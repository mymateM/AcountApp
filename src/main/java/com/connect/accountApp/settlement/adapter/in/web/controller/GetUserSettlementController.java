package com.connect.accountApp.settlement.adapter.in.web.controller;

import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.settlement.adapter.in.web.response.UserSettlementResponse;
import com.connect.accountApp.settlement.application.port.in.GetUserSettlementUseCase;
import com.connect.accountApp.settlement.application.port.in.command.UserSettlementCommand;
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
    System.out.println("------------------");

    System.out.println("userDetails = " + userDetails);
    System.out.println("startDate = " + startDate);
    System.out.println("endDate = " + endDate);
    String userEmail = userDetails.getUsername();
    System.out.println("userEmail = " + userEmail);
    UserSettlementCommand command = getUserSettlementUseCase.getUserSettlement(userEmail, startDate, endDate);
    UserSettlementResponse response = new UserSettlementResponse(command, startDate, endDate);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
