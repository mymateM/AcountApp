package com.connect.accountApp.domain.settlement.adapter.in.web.controller;

import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.domain.settlement.adapter.in.web.response.UserSettlementResponse;
import com.connect.accountApp.domain.settlement.application.port.in.GetUserSettlementUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementWithHouseholdTotalExpenseCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/settlement")
public class GetUserSettlementController {

  private final GetUserSettlementUseCase getUserSettlementUseCase;

  @GetMapping("/user")
  public ResponseEntity getUserSettlement(@AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {

    String userEmail = userDetails.getUsername();
    UserSettlementWithHouseholdTotalExpenseCommand command = getUserSettlementUseCase.getUserSettlement(userEmail, startDate, endDate);
    UserSettlementResponse response = new UserSettlementResponse(command, startDate, endDate);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
