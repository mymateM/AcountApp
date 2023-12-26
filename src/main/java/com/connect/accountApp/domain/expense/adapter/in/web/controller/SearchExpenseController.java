package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.DailyExpenseResponse;
import com.connect.accountApp.domain.expense.application.port.in.SearchExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SearchedCondition;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
@RequiredArgsConstructor
public class SearchExpenseController {

    private final SearchExpenseUseCase searchExpenseUseCase;

    @PostMapping("/search")
    public ResponseEntity searchExpense(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam("expense_date_max") LocalDate expenseDateMax,
                                        @RequestParam("expense_date_min") LocalDate expenseDateMin,
                                        @RequestParam("expense_category_name") String expenseCategoryName,
                                        @RequestParam("expense_amount_max") BigDecimal expenseAmountMax,
                                        @RequestParam("expense_amount_min") BigDecimal expenseAmountMin,
                                        @RequestParam("sorted_by_newest") Boolean sortedByNewest) {

        String userEmail = userDetails.getUsername();
        SearchedCondition condition =
                new SearchedCondition(
                        expenseDateMax, expenseDateMin, expenseCategoryName,
                        expenseAmountMax, expenseAmountMin, sortedByNewest);
        List<DailyExpenseCommand> commands = searchExpenseUseCase.getSearchedExpense(userEmail, condition);
        DailyExpenseResponse response = new DailyExpenseResponse(commands);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }
}
