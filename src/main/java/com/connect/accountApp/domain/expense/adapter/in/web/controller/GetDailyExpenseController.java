package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.DailyExpenseResponse;
import com.connect.accountApp.domain.expense.application.port.in.GetDailyExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.time.LocalDate;
import java.util.List;
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
public class GetDailyExpenseController {

  private final GetDailyExpenseUseCase getDailyExpenseUseCase;

  @GetMapping("/daily-total/day/{year}/{month}/{dayOfMonth}")
  public ResponseEntity getDailyExpenseOfDay(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable("year") int year,
      @PathVariable("month") int month,
      @PathVariable("dayOfMonth") int dayOfMonth) {

    String userEmail = userDetails.getUsername();
    LocalDate date = LocalDate.of(year, month, dayOfMonth);

    List<DailyExpenseCommand> commands = getDailyExpenseUseCase.getDailyExpense(userEmail, date);
    DailyExpenseResponse response = new DailyExpenseResponse(commands);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));

  }


}
