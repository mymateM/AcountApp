package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetMembersSettlementRatioResponse;
import com.connect.accountApp.domain.household.application.port.in.GetMembersSettlementRatioUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.GetMembersSettlementCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class GetMembersSettlementRatioController {

  private final GetMembersSettlementRatioUseCase getMembersSettlementRatioUseCase;

  @GetMapping("/members/settlement-ratio")
  public ResponseEntity getBudgetAllowanceAmount(@AuthenticationPrincipal UserDetails userDetails) {
    String userEmail = userDetails.getUsername();

    List<GetMembersSettlementCommand> commands = getMembersSettlementRatioUseCase.getMembersSettlementRatio(userEmail);
    GetMembersSettlementRatioResponse response = new GetMembersSettlementRatioResponse(commands);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }


}
