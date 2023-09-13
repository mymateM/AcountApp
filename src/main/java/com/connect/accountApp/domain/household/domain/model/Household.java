package com.connect.accountApp.domain.household.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Household {

  private Long householdId;
  private String householdName;
  private LocalDate householdSettlementDate;
  private BigDecimal householdBudget;
  private Integer householdBudgetAllowanceRatio;
  private boolean householdAccept;
  private LocalDateTime createdAt;
  private Integer householdSettlementDayOfMonth;

  private String inviteCode;


  @Builder
  public Household(Long householdId, String householdName, LocalDate householdSettlementDate, BigDecimal householdBudget,
      Integer householdBudgetAllowanceRatio, boolean householdAccept, LocalDateTime createdAt, Integer householdSettlementDayOfMonth,
      String inviteCode) {
    this.householdId = householdId;
    this.householdName = householdName;
    this.householdSettlementDate = householdSettlementDate;
    this.householdBudget = householdBudget;
    this.householdBudgetAllowanceRatio = householdBudgetAllowanceRatio;
    this.householdAccept = householdAccept;
    this.createdAt = createdAt;
    this.householdSettlementDayOfMonth = householdSettlementDayOfMonth;
    this.inviteCode = inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }
}
