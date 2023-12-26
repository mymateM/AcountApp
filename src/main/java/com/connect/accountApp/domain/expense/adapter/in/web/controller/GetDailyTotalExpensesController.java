package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.GetDailyTotalExpensesOfHouseholdResponse;
import com.connect.accountApp.domain.expense.application.port.in.GetDailyTotalExpensesOfHouseholdUseCase;
import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
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
public class GetDailyTotalExpensesController { // 월별 지출 캘린더

  private final GetDailyTotalExpensesOfHouseholdUseCase getDailyTotalExpensesOfHouseholdUseCase;

  @GetMapping("/daily-total/month/{year}/{month}/{dayOfMonth}")
  public ResponseEntity getDailyTotalExpenses(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable("year") int year,
                                              @PathVariable("month") int month,
                                              @PathVariable("dayOfMonth") int dayOfMonth) {

    String userEmail = userDetails.getUsername();
    LocalDate date = LocalDate.of(year, month, dayOfMonth);

    List<DailyTotalExpensesCommand> command = getDailyTotalExpensesOfHouseholdUseCase.getDailyTotalExpensesOfHousehold(userEmail, date);

    GetDailyTotalExpensesOfHouseholdResponse response = new GetDailyTotalExpensesOfHouseholdResponse(command);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }
}
