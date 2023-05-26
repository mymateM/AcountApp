package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.HomeResponse;
import com.connect.accountApp.domain.expense.application.port.in.command.HomeCommand;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class getHomeController {

  private final GetHouseholdSettlementUseCase getHouseholdSettlementUseCase;

  @GetMapping("/home/{userId}")
  public ResponseEntity getHouseholdSettlement(@PathVariable Long userId) {

    HomeCommand command = getHouseholdSettlementUseCase.getHouseholdSettlement(userId);

    HomeResponse response = new HomeResponse(command);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }
}
