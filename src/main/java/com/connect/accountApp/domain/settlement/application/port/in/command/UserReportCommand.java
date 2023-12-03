package com.connect.accountApp.domain.settlement.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseByCategoryCommand;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserReportCommand {


    private ReportDateCommand reportDate;
    private List<ExpenseCommandByCategory> expenseCommandByCategories;

    public UserReportCommand(ReportDateCommand reportDate, List<ExpenseCommandByCategory> expenseCommandByCategories) {
        this.reportDate = reportDate;
        this.expenseCommandByCategories = expenseCommandByCategories;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReportDateCommand {

        private LocalDate dateStart;
        private LocalDate dateEnd;

        public ReportDateCommand(LocalDate dateStart, LocalDate dateEnd) {
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ExpenseCommandByCategory {
        private String expenseCategoryTitle;
        private double expenseSumRatio;
        private BigDecimal expenseSum;
        private String expenseCategoryImage;


        public ExpenseCommandByCategory(TotalExpenseByCategoryCommand expenseCommandByCategory, BigDecimal totalExpenseAmount) {
            this.expenseCategoryTitle = expenseCommandByCategory.getExpenseCategory().getTitle();
            this.expenseSum = expenseCommandByCategory.getTotalExpenseAmount();
            this.expenseSumRatio = expenseCommandByCategory.getTotalExpenseAmount()
                    .divide(totalExpenseAmount, 2, RoundingMode.FLOOR).doubleValue();
            this.expenseCategoryImage = expenseCommandByCategory.getExpenseCategory().getImgUrl();
        }

    }
}
