package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;


import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetExpenseResponse {

    // 지출금액, 메모, 카테고리, 거래처, 등록일
    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;
    @JsonProperty("expense_memo")
    private String expenseMemo;
    @JsonProperty("expense_category")
    private String expenseCategory;
    @JsonProperty("expense_store")
    private String expenseStore;
    @JsonProperty("expense_register_date")
    private LocalDate expenseDate;

    public GetExpenseResponse(Expense expense) {
        this.expenseDate = expense.getExpenseDate();
        this.expenseMemo = expense.getExpenseMemo();
        this.expenseCategory = expense.getExpenseCategory().getTitle();
        this.expenseStore = expense.getExpenseStore();
        this.paymentAmount = expense.getExpenseAmount();
    }
}
