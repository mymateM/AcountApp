package com.connect.accountApp.domain.settlement.domain.model;

import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settlement {

  private Long settlementId;
  private Expense expense;
  private User user;
  private Boolean isSettlementDelegate;

  @Builder
  public Settlement(Long settlementId, Expense expense, User user, Boolean isSettlementDelegate) {
    this.settlementId = settlementId;
    this.expense = expense;
    this.user = user;
    this.isSettlementDelegate = isSettlementDelegate;
  }
}
