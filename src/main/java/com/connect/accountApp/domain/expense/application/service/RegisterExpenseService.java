package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.request.RegisterExpenseRequest;
import com.connect.accountApp.domain.expense.application.port.in.RegisterExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.SaveExpensePort;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.settlement.application.port.out.SaveSettlementPort;
import com.connect.accountApp.domain.settlement.domain.model.Settlement;
import com.connect.accountApp.domain.settlement.exception.ExpenseDelegateNotFound;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterExpenseService implements RegisterExpenseUseCase {

  private final GetUserPort getUserPort;
  private final SaveExpensePort saveExpensePort;
  private final FindExpensePort findExpensePort;
  private final SaveSettlementPort saveSettlementPort;


  @Override

  public Long registerExpense(String expenseDelegateUserEmail, RegisterExpenseRequest request) {

    User expenseDelegate = getUserPort.findUser(expenseDelegateUserEmail);
    List<User> settlementSubjects = request.getSettlementSubjectIds().stream().map(getUserPort::getUser).toList();

    validateDelegateExistInSubjects(expenseDelegate, settlementSubjects);

    Expense expense = createAndSaveExpense(request);
    createAndSaveSettlementsOfSubjects(settlementSubjects, expense, expenseDelegate);

    return expense.getExpenseId();
  }


  private void validateDelegateExistInSubjects(User expenseDelegate, List<User> settlementSubjects) {
    boolean existExistSubjects = settlementSubjects.stream()
        .anyMatch(user -> Objects.equals(user.getUserId(), expenseDelegate.getUserId()));

    if (!existExistSubjects)
      throw new ExpenseDelegateNotFound("실제 지출 등록한 사람의 id : " + expenseDelegate.getUserId() +
          "가 settlementSubjects : " + settlementSubjects + "에 존재하지 않습니다.");
  }

  private void createAndSaveSettlementsOfSubjects(List<User> settlementSubjects, Expense expense, User expenseDelegate) {
    settlementSubjects.forEach(settlementSubject ->
        {
          Settlement settlement = createSettlement(expense, expenseDelegate, settlementSubject);
          saveSettlementPort.saveSettlement(settlement);
        }
    );
  }

  private Expense createAndSaveExpense(RegisterExpenseRequest request) {
    Expense newExpense = createExpense(request);
    Long savedExpenseId = saveExpensePort.saveExpensePort(newExpense);
    return findExpensePort.findExpense(savedExpenseId);
  }

  private Expense createExpense(RegisterExpenseRequest request) {
    return Expense.builder()
        .expenseAmount(request.getExpenseAmount())
        .expenseDate(request.getExpenseDate())
        .expenseStore(request.getExpenseStore())
        .expenseMemo(request.getExpenseMemo())
        .expenseCategory(request.getExpenseCategory())
        .build();
  }

  private Settlement createSettlement(Expense expense, User expenseDelegate, User settlementSubject) {
    return Settlement.builder()
        .expense(expense)
        .user(settlementSubject)
        .isSettlementDelegate(Objects.equals(settlementSubject.getUserId(), expenseDelegate.getUserId()))
        .build();
  }
}
