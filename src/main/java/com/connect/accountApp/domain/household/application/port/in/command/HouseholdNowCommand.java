package com.connect.accountApp.domain.household.application.port.in.command;

import static java.time.temporal.ChronoUnit.DAYS;

import com.connect.accountApp.domain.household.domain.model.Household;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdNowCommand {


  private Long householdId;
  private LocalDate settlementDate;
  private int householdSettlementDDay;
  private int householdBudget;
  private int householdByPreviousExpense;
  private int householdByNowExpense;
  private int householdNowExpenseDiff;
  private boolean householdBudgetOverWarn;


  public HouseholdNowCommand(Household household, LocalDate settlementDate, int householdByNowExpense, int householdByPreviousExpense) {
    this.householdId = household.getHouseholdId();
    this.settlementDate = household.getHouseholdSettlementDate();
    this.householdSettlementDDay = calHouseholdSettlementDDay(settlementDate);
    this.householdBudget = household.getHouseholdBudget();
    this.householdByPreviousExpense = householdByPreviousExpense;
    this.householdByNowExpense = householdByNowExpense;
    this.householdNowExpenseDiff = calHouseholdNowExpenseDiff();
    this.householdBudgetOverWarn = isHouseholdBudgetOverWarn();
  }

  private int calHouseholdSettlementDDay(LocalDate settlementDate) {
    return Long.valueOf(DAYS.between(settlementDate, LocalDate.now())).intValue();
  }

  private int calHouseholdNowExpenseDiff() {
    return (this.householdByNowExpense - this.householdByPreviousExpense);
  }

  // TODO: 기준 정하기
  private boolean isHouseholdBudgetOverWarn() {
    return true;
  }

}
