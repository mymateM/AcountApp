package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import lombok.Getter;

@Getter
public class SearchedCondition {

    private LocalDate expenseDateMax;
    private LocalDate expenseDateMin;
    private Optional<ExpenseCategory> expenseCategory;
    private BigDecimal expenseAmountMax;
    private BigDecimal expenseAmountMin;
    private Boolean sortedByNewest;

    public SearchedCondition(LocalDate expenseDateMax, LocalDate expenseDateMin, String expenseCategoryName,
                             BigDecimal expenseAmountMax, BigDecimal expenseAmountMin, Boolean sortedBy) {
        this.expenseDateMax = expenseDateMax;
        this.expenseDateMin = expenseDateMin;
        this.expenseCategory = findExpenseCategory(expenseCategoryName);
        this.expenseAmountMax = expenseAmountMax;
        this.expenseAmountMin = expenseAmountMin;
        this.sortedByNewest = sortedBy;
    }

    private Optional<ExpenseCategory> findExpenseCategory(String expenseCategoryName) {
        if (expenseCategoryName.equals("")) {
            return Optional.empty();
        }
        return Optional.of(ExpenseCategory.valueOf(expenseCategoryName));
    }

    @Override
    public String toString() {
        return "SearchedCondition{" +
                "expenseDateMax=" + expenseDateMax +
                ", expenseDateMin=" + expenseDateMin +
                ", expenseCategory=" + expenseCategory +
                ", expenseAmountMax=" + expenseAmountMax +
                ", expenseAmountMin=" + expenseAmountMin +
                ", sortedByNewest=" + sortedByNewest +
                '}';
    }
}
