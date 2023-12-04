package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.bill.application.port.out.DeleteBillPort;
import com.connect.accountApp.domain.expense.application.port.in.DeleteExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.out.DeleteExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.expensenotification.application.port.out.DeleteExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.application.port.out.FindExpenseNotificationPort;
import com.connect.accountApp.domain.expensenotification.domain.model.ExpenseNotification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteExpenseService implements DeleteExpenseUseCase {

    private final FindExpenseNotificationPort findExpenseNotificationPort;
    private final DeleteExpenseNotificationPort deleteExpenseNotificationPort;
    private final FindExpensePort findExpensePort;
    private final DeleteExpensePort deleteExpensePort;

    @Override
    public void deleteExpense(Long expenseId) {

        Expense expense = findExpensePort.findExpense(expenseId);
        List<ExpenseNotification> expenseNotifications =
                findExpenseNotificationPort.findExpenseNotifications(expense.getExpenseId());
        List<Long> expenseIds = expenseNotifications.stream().map(ExpenseNotification::getId).toList();
        deleteExpenseNotificationPort.deleteExpenseNotification(expenseIds);
        deleteExpensePort.deleteExpense(expenseId);
    }
}
