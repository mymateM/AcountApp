package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.request.RegisterExpenseRequest;
import com.connect.accountApp.domain.expense.application.port.in.RegisterExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.SaveExpensePort;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.expensenotification.application.port.out.SaveExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterExpenseService implements RegisterExpenseUseCase {

    private final GetUserPort getUserPort;
    private final SaveExpensePort saveExpensePort;
    private final FindExpensePort findExpensePort;
    private final SaveExpenseNotificationPort saveExpenseNotificationPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;


    @Override
    public Long registerExpense(String expenseDelegateUserEmail, RegisterExpenseRequest request) {
        User userWithHousehold = getUserPort.findUserWithHousehold(expenseDelegateUserEmail);
        Household household = userWithHousehold.getHousehold();

        Expense expense = createAndSaveExpense(request, household, userWithHousehold);
        saveExpenseNotifications(expense, household);

        return expense.getExpenseId();
    }


    private Expense createAndSaveExpense(RegisterExpenseRequest request, Household household, User spender) {
        Expense newExpense = createExpense(request, household, spender);
        Long savedExpenseId = saveExpensePort.saveExpensePort(newExpense);
        return findExpensePort.findExpense(savedExpenseId);
    }


    private Expense createExpense(RegisterExpenseRequest request, Household household, User spender) {

        return Expense.builder()
                .expenseAmount(request.getExpenseAmount())
                .expenseDate(request.getExpenseDate())
                .expenseStore(request.getExpenseStore())
                .expenseMemo(request.getExpenseMemo())
                .expenseCategory(request.getExpenseCategory())
                .household(household)
                .spender(spender)
                .build();
    }


    private void saveExpenseNotifications(Expense expense, Household household) {
        List<ExpenseNotification> expenseNotification = createExpenseNotificationOfMembers(expense, household);
        saveExpenseNotificationPort.saveAll(expenseNotification);
    }

    private List<ExpenseNotification> createExpenseNotificationOfMembers(Expense expense, Household household) {
        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(household.getHouseholdId());
        return householdMembers.stream()
                .map(member -> createExpenseNotification(expense, member))
                .collect(Collectors.toList());
    }

    private ExpenseNotification createExpenseNotification(Expense expense, User member) {
        return ExpenseNotification.builder()
                .isRead(false)
                .expense(expense)
                .user(member).build();
    }

}
