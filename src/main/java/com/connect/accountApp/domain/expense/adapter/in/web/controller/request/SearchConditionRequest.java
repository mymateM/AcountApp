package com.connect.accountApp.domain.expense.adapter.in.web.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchConditionRequest {

    @JsonProperty("expense_date")
    private ExpenseDate expenseDate;
    @JsonProperty("expense_category_name")
    private String expenseCategoryName;
    @JsonProperty("expense_amount")
    private ExpenseAmount expenseAmount;
    @JsonProperty("sortedBy")
    public String sortedBy;


    @Getter
    @NoArgsConstructor
    public class ExpenseDate {
        @JsonProperty("date_start")
        private LocalDate dateStart;
        @JsonProperty("date_end")
        private LocalDate dateEnd;
    }

    @Getter
    @NoArgsConstructor
    public class ExpenseAmount {
        @JsonProperty("amount_max")
        private BigDecimal amountMax;
        @JsonProperty("amount_min")
        private BigDecimal amountMin;
    }

}
