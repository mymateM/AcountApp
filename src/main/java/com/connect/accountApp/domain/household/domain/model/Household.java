package com.connect.accountApp.domain.household.domain.model;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Household {

  private Long householdId;
  private LocalDate householdSettlementDate;
  private int householdBudget;
  private boolean householdAccept;

  @Builder
  public Household(Long householdId, LocalDate householdSettlementDate, int householdBudget,
      boolean householdAccept) {
    this.householdId = householdId;
    this.householdSettlementDate = householdSettlementDate;
    this.householdBudget = householdBudget;
    this.householdAccept = householdAccept;
  }
}
