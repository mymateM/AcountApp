package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.GetExpenseResponse;
import com.connect.accountApp.domain.expense.application.port.in.GetExpenseUseCase;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
@RequiredArgsConstructor
public class GetExpenseController {

    private final GetExpenseUseCase getExpenseUseCase;

    @GetMapping("/{expense_id}")
    public ResponseEntity getDailyExpenseOfDay(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("expense_id") Long expenseId) {

        String userEmail = userDetails.getUsername();

        Expense expense = getExpenseUseCase.getExpense(expenseId);
        GetExpenseResponse response = new GetExpenseResponse(expense);

        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));

    }


}
