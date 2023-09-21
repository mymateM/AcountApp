package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.request.RegisterExpenseRequest;
import com.connect.accountApp.domain.expense.application.port.in.RegisterExpenseUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
@RequiredArgsConstructor
public class RegisterExpenseController {

  private final RegisterExpenseUseCase registerExpenseUseCase;


  @PostMapping("")
  public ResponseEntity registerExpense(@AuthenticationPrincipal UserDetails userDetails,
      @RequestBody RegisterExpenseRequest request) {

    String userEmail = userDetails.getUsername();
    Long expenseId = registerExpenseUseCase.registerExpense(userEmail, request);
    return ResponseEntity.ok(SuccessResponse.create201CreatedResponse(expenseId));
  }

}
