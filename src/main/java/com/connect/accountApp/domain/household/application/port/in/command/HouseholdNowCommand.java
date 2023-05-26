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
  private int householdSettlementDDay;
  private int householdBudget;
  private int householdByPreviousExpense;
  private int householdByNowExpense;
  private int householdNowExpenseDiff;
  private boolean householdBudgetOverWarn;


  public HouseholdNowCommand(Household household, LocalDate pastNearSettlementDate, int householdByNowExpense, int householdByPreviousExpense) {
    this.householdId = household.getHouseholdId();
    this.householdSettlementDDay = calHouseholdSettlementDDay(pastNearSettlementDate);
    this.householdBudget = household.getHouseholdBudget();
    this.householdByPreviousExpense = householdByPreviousExpense;
    this.householdByNowExpense = householdByNowExpense;
    this.householdNowExpenseDiff = calHouseholdNowExpenseDiff();
    this.householdBudgetOverWarn = isHouseholdBudgetOverWarn(household.getHouseholdBudget(),
        pastNearSettlementDate, householdByNowExpense);
  }

  private int calHouseholdSettlementDDay(LocalDate settlementDate) {
    return dayDiff(LocalDate.now(), settlementDate.plusMonths(1));
  }

  private int calHouseholdNowExpenseDiff() {
    return (this.householdByNowExpense - this.householdByPreviousExpense);
  }

  private boolean isHouseholdBudgetOverWarn(int budget, LocalDate pastNearSettlementDate, int householdByNowExpense) {

    int passDay = dayDiff(pastNearSettlementDate, LocalDate.now());
    int settlementDayDiff = dayDiff(pastNearSettlementDate, pastNearSettlementDate.plusMonths(1));
    int standard = (budget / settlementDayDiff) * passDay;
    System.out.println("standard" + standard);
    System.out.println("householdByNowExpense" + householdByNowExpense);

    return standard <= householdByNowExpense;
  }


  private int dayDiff(LocalDate startDate, LocalDate endDate) {
    return Long.valueOf(DAYS.between(startDate, endDate)).intValue();
  }

}
