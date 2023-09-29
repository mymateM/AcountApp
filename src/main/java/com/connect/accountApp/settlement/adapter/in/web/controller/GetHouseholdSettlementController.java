package com.connect.accountApp.settlement.adapter.in.web.controller;

import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.settlement.adapter.in.web.response.HouseholdSettlementResponse;
import com.connect.accountApp.settlement.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.settlement.application.port.in.command.HouseholdSettlementCommand;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/settlement")
@RequiredArgsConstructor
@Slf4j
public class GetHouseholdSettlementController {

  private final GetHouseholdSettlementUseCase getHouseholdSettlementUseCase;

  @GetMapping("")
  public ResponseEntity getRoommateSettlement(@AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {

    //TODO : 실제 정산일이 맞는지 체크하는 로직 필요

    log.info("[GetRoommateSettlementController]");

    String userEmail = userDetails.getUsername();

    System.out.println("userEmail = " + userEmail);





    HouseholdSettlementCommand command = getHouseholdSettlementUseCase.getRoommateSettlement(
        userEmail, startDate, endDate);

    HouseholdSettlementResponse response = new HouseholdSettlementResponse(command, startDate, endDate);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
