package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyExpenseCommand {

  private Long expenseId;
  private BigDecimal expenseAmount;
  private String expenseStore;
  private ExpenseCategory expenseCategory;
  private List<SettlementSubjectCommand> settlementSubjects;

  public DailyExpenseCommand(Long expenseId, BigDecimal expenseAmount, String expenseStore,
      ExpenseCategory expenseCategory,
      List<SettlementSubjectCommand> settlementSubjects) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseStore = expenseStore;
    this.expenseCategory = expenseCategory;
    this.settlementSubjects = settlementSubjects;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SettlementSubjectCommand {

    private Long userId;
    private String userNickname;
    private Boolean isExpenseConsumer;
    private String userProfileImage;

    public SettlementSubjectCommand(Long userId, String userNickname, Boolean isExpenseConsumer,
        String userProfileImage) {
      this.userId = userId;
      this.userNickname = userNickname;
      this.isExpenseConsumer = isExpenseConsumer;
      this.userProfileImage = userProfileImage;
    }
  }

}
