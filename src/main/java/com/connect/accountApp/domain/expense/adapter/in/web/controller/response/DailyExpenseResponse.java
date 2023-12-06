package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;

import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyExpenseResponse {

    private List<DailyExpense> expenses;

    public DailyExpenseResponse(List<DailyExpenseCommand> commands) {
        this.expenses = commands.stream().map(DailyExpense::new).toList();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class DailyExpense {

        private Long expenseId;
        private BigDecimal expenseAmount;
        private String expenseStore;
        private String expenseCategoryName;
        private String expenseCategoryImage;
        private List settlementSubjects;
        private LocalDate expenseDate;

        public DailyExpense(DailyExpenseCommand command) {
            this.expenseId = command.getExpenseId();
            this.expenseAmount = command.getExpenseAmount().setScale(0, RoundingMode.FLOOR);
            this.expenseStore = command.getExpenseStore();
            this.expenseCategoryName = command.getExpenseCategory().getTitle();
            this.expenseCategoryImage = command.getExpenseCategory().getImgUrl();
            this.settlementSubjects = Collections.EMPTY_LIST;
            this.expenseDate = command.getExpenseDate();
        }
    }

}
