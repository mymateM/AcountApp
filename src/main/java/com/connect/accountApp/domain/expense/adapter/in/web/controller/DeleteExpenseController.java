package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.DailyExpenseResponse;
import com.connect.accountApp.domain.expense.application.port.in.DeleteExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
@RequiredArgsConstructor
public class DeleteExpenseController {

    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @DeleteMapping("/{expense_id}")
    public ResponseEntity getDailyExpenseOfDay(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable("expense_id") Long expenseId) {
        String username = userDetails.getUsername();
        deleteExpenseUseCase.deleteExpense(expenseId);
        return ResponseEntity.ok(SuccessResponse.create204NoContentResponse());

    }
}
