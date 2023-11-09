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
  private boolean settlementWillBeUpdated;
  private LocalDateTime createdAt;
  private Integer householdSettlementDayOfMonth;


  private String inviteCode;


  @Builder
  public Household(Long householdId, String householdName, LocalDate householdSettlementDate, BigDecimal householdBudget,
      Integer householdBudgetAllowanceRatio, boolean settlementWillBeUpdated, LocalDateTime createdAt, Integer householdSettlementDayOfMonth,
      String inviteCode) {
    this.householdId = householdId;
    this.householdName = householdName;
    this.householdSettlementDate = householdSettlementDate;
    this.householdBudget = householdBudget;
    this.householdBudgetAllowanceRatio = householdBudgetAllowanceRatio;
    this.settlementWillBeUpdated = settlementWillBeUpdated;
    this.createdAt = createdAt;
    this.householdSettlementDayOfMonth = householdSettlementDayOfMonth;
    this.inviteCode = inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }

  public void updateHouseholdSettlementDayOfMonth(Integer householdSettlementDayOfMonth) {
    setHouseholdSettlementDayOfMonth(householdSettlementDayOfMonth);
  }

  private void setHouseholdSettlementDayOfMonth(Integer householdSettlementDayOfMonth) {
    this.householdSettlementDayOfMonth = householdSettlementDayOfMonth;
  }

  public void updateHouseholdBudget(BigDecimal householdBudget) {
    setHouseholdBudget(householdBudget);
  }

  public void updateRatioAllowance(Integer allowanceRatio) {
    setHouseholdBudgetAllowanceRatio(allowanceRatio);
  }
  private void setHouseholdBudget(BigDecimal householdBudget) {
    this.householdBudget = householdBudget;
  }

  public void setSettlementWillBeUpdatedToTrue() {
    setSettlementWillBeUpdated(true);
  }

  public void setSettlementWillBeUpdatedToFalse() {
    setSettlementWillBeUpdated(false);
  }

  private void setSettlementWillBeUpdated(boolean settlementWillBeUpdated) {
    this.settlementWillBeUpdated = settlementWillBeUpdated;
  }

  private void setHouseholdBudgetAllowanceRatio(Integer householdBudgetAllowanceRatio) {
    this.householdBudgetAllowanceRatio = householdBudgetAllowanceRatio;
  }
}
