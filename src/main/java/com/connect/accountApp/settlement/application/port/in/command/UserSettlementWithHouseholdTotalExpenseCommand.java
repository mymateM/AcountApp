package com.connect.accountApp.settlement.application.port.in.command;

import static lombok.AccessLevel.PROTECTED;

import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserSettlementWithHouseholdTotalExpenseCommand {

  private BigDecimal householdExpenseTotal;
  private UserSettlementCommand userSettlementCommand;

  public UserSettlementWithHouseholdTotalExpenseCommand(BigDecimal householdExpenseTotal, BigDecimal realExpense, BigDecimal ratioExpense,
      User user) {
    this.householdExpenseTotal = householdExpenseTotal;
    this.userSettlementCommand = new UserSettlementCommand(user.getUserId(), user.getUserNickname(), realExpense, ratioExpense);
  }


}
