package com.connect.accountApp.domain.settlement.adapter.in.web.controller;


import com.connect.accountApp.domain.settlement.adapter.in.web.response.HouseholdReportResponse;
import com.connect.accountApp.domain.settlement.adapter.in.web.response.UserExpenseByCategoryResponse;
import com.connect.accountApp.domain.settlement.application.port.in.GetUserReportUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand;
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
public class GetUserReportController {

    private final GetUserReportUseCase getUserReportUseCase;

    @GetMapping("/user/{report-start-date}")
    public ResponseEntity getHouseholdReport(@AuthenticationPrincipal UserDetails userDetails,
                                             @PathVariable("report-start-date") LocalDate startDate) {
        String userEmail = userDetails.getUsername();

        UserReportCommand command = getUserReportUseCase.getUserReport(userEmail, startDate);
        UserExpenseByCategoryResponse response = new UserExpenseByCategoryResponse(command);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }

}
