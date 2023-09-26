package com.connect.accountApp.settlement.application.port.in.command;

import static lombok.AccessLevel.PROTECTED;

import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserSettlementCommand {

  private BigDecimal householdExpenseTotal;
  private UserCommand userCommand;

  public UserSettlementCommand(BigDecimal householdExpenseTotal, BigDecimal realExpense, BigDecimal ratioExpense,
      User user) {
    this.householdExpenseTotal = householdExpenseTotal;
    this.userCommand = new UserCommand(user.getUserId(), user.getUserNickname(), realExpense, ratioExpense);
  }

  @Getter
  @NoArgsConstructor(access = PROTECTED)
  public class UserCommand {

    private Long id;
    private String name;
    private BigDecimal realExpense;
    private BigDecimal ratioExpense;
    private Boolean isSettlementSender;
    private BigDecimal settlementAmount;

    public UserCommand(Long id, String name, BigDecimal realExpense, BigDecimal ratioExpense) {
      this.id = id;
      this.name = name;
      this.realExpense = realExpense;
      this.ratioExpense = ratioExpense;
      this.isSettlementSender = realExpense.compareTo(ratioExpense) < 0; // realExpense < ratioExpense : 보내는 사람
      this.settlementAmount = realExpense.subtract(ratioExpense).abs();
    }
  }

}
