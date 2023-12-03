package com.connect.accountApp.domain.settlement.adapter.in.web.response;

import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand.ExpenseCommandByCategory;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand.ReportDateCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserExpenseByCategoryResponse {


    @JsonProperty("report_date")
    private ReportDateResponse reportDateCommand;
    @JsonProperty("expense_categories")
    private List<ExpenseResponseByCategory> expenseResponseByCategories;

    public UserExpenseByCategoryResponse(UserReportCommand userReportCommand) {
        this.reportDateCommand = new ReportDateResponse(userReportCommand.getReportDate());
        this.expenseResponseByCategories = userReportCommand.getExpenseCommandByCategories()
                .stream().map(ExpenseResponseByCategory::new).collect(Collectors.toList());

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
