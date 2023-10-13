package com.connect.accountApp.domain.settlement.adapter.in.web.controller;

import com.connect.accountApp.domain.settlement.adapter.in.web.response.HouseholdReportResponse;
import com.connect.accountApp.domain.settlement.application.port.in.GetHouseholdReportUseCase;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class GetHouseholdReportController {


  private final GetHouseholdReportUseCase getHouseholdReportUseCase;

  @GetMapping("/household/{report-start-date}")
  public ResponseEntity getHouseholdReport(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable("report-start-date") LocalDate startDate) {
    String userEmail = userDetails.getUsername();

    HouseholdReportCommand command = getHouseholdReportUseCase.getHouseholdReport(userEmail, startDate);
    HouseholdReportResponse response = new HouseholdReportResponse(command);
    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
