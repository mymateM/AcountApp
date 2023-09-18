package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;

import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetDailyTotalExpensesOfHouseholdResponse {

  @JsonProperty("household_daily_expenses")
  private List<DailyTotalExpense> dailyTotalExpenses;

  public GetDailyTotalExpensesOfHouseholdResponse(List<DailyTotalExpensesCommand> commands) {

    this.dailyTotalExpenses = commands.stream().map(DailyTotalExpense::new).toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  class DailyTotalExpense {

    @JsonProperty("expense_date")
    private Integer expenseDate;

    @JsonProperty("daily_total_expense")
    private BigDecimal dailyTotalExpense;

    public DailyTotalExpense(DailyTotalExpensesCommand command) {
      this.expenseDate = command.getExpenseDayOfMonth();
      this.dailyTotalExpense = command.getDailyTotalExpense();
    }
  }

}
