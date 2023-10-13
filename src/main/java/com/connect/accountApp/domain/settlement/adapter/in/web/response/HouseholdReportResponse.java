package com.connect.accountApp.domain.settlement.adapter.in.web.response;

import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand.ExpenseCommandByCategory;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand.ReportDateCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdReportResponse {

  @JsonProperty("report_date")
  private ReportDateResponse reportDateCommand;
  @JsonProperty("is_expense_over_budget")
  private Boolean isExpenseOverBudget;
  @JsonProperty("buget_real_expense_diff")
  private BigDecimal budgetRealExpenseDiff;
  @JsonProperty("total_expense")
  private BigDecimal totalExpense;
  @JsonProperty("expense_categories")
  private List<ExpenseResponseByCategory> expenseResponseByCategories;

  public HouseholdReportResponse(HouseholdReportCommand command) {

    this.reportDateCommand = new ReportDateResponse(command.getReportDate());
    this.isExpenseOverBudget = command.getIsExpenseOverBudget();
    this.budgetRealExpenseDiff = command.getBudgetRealExpenseDiff().setScale(0, RoundingMode.FLOOR);
    this.totalExpense = command.getTotalExpense().setScale(0, RoundingMode.FLOOR);;
    this.expenseResponseByCategories = command.getExpenseCommandByCategories()
        .stream().map(ExpenseResponseByCategory::new).toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class ReportDateResponse {

    @JsonProperty("date_start")
    private LocalDate dateStart;
    @JsonProperty("date_end")
    private LocalDate dateEnd;

    public ReportDateResponse(ReportDateCommand command) {
      this.dateStart = command.getDateStart();
      this.dateEnd = command.getDateEnd();
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class ExpenseResponseByCategory {
    @JsonProperty("category_name")
    private String expenseCategoryTitle;
    @JsonProperty("category_img")
    private String expenseCategoryImage;
    @JsonProperty("total_expense_ratio")
    private double expenseSumRatio;
    @JsonProperty("total_expense_amount")
    private BigDecimal expenseSum;


    public ExpenseResponseByCategory(ExpenseCommandByCategory command) {
      this.expenseCategoryTitle = command.getExpenseCategoryTitle();
      this.expenseSumRatio = command.getExpenseSumRatio();
      this.expenseSum = command.getExpenseSum().setScale(0, RoundingMode.FLOOR);
      this.expenseCategoryImage = command.getExpenseCategoryImage();
    }
  }


}
