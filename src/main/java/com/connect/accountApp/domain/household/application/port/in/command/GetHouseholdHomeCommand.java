package com.connect.accountApp.domain.household.application.port.in.command;

import static java.time.temporal.ChronoUnit.DAYS;

import com.connect.accountApp.domain.household.domain.model.Household;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetHouseholdHomeCommand {

    private Long householdId;
    private String householdName;
    private BigDecimal byNowExpense;
    private BigDecimal byNowBudgetRatio;
    private Integer settlementDDay;
    private BigDecimal byPreviousExpense;
    private BigDecimal nowExpenseDiff;
    private Boolean isHouseholdBudgetOverWarn;
    private Integer expenseDuration;


    public GetHouseholdHomeCommand(Household household, BigDecimal byNowExpense, BigDecimal byPreviousExpense, int dDay, LocalDate pastNearSettlementDate) {
        this.householdId = household.getHouseholdId();
        this.householdName = household.getHouseholdName();
        this.byNowExpense = byNowExpense ;
        this.byPreviousExpense = byPreviousExpense;
        this.byNowBudgetRatio = byNowExpense.divide(household.getHouseholdBudget()).multiply(BigDecimal.valueOf(100L));
        this.settlementDDay = dDay;
        this.nowExpenseDiff = byPreviousExpense.subtract(byNowExpense);
        this.isHouseholdBudgetOverWarn = isHouseholdBudgetOverWarn(household.getHouseholdBudget(), pastNearSettlementDate, byNowExpense);
        this.expenseDuration = Period.between(pastNearSettlementDate, LocalDate.now()).getDays();
    }

    private boolean isHouseholdBudgetOverWarn(BigDecimal budget, LocalDate pastNearSettlementDate, BigDecimal householdByNowExpense) {

        int passDay = dayDiff(pastNearSettlementDate, LocalDate.now());
        int settlementDayDiff = dayDiff(pastNearSettlementDate, pastNearSettlementDate.plusMonths(1));
        BigDecimal standard = budget.divide(BigDecimal.valueOf(settlementDayDiff), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(passDay));

        return standard.compareTo(householdByNowExpense) <= 0;
    }

    private int dayDiff(LocalDate startDate, LocalDate endDate) {
        return Long.valueOf(DAYS.between(startDate, endDate)).intValue();
    }
}
