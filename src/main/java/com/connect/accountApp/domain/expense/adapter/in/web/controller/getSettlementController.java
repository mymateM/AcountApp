package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.GetSettlementResponse;
import com.connect.accountApp.domain.expense.application.port.in.GetSettlementUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.SettlementCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class getSettlementController {

  private final GetSettlementUseCase getSettlementUseCase;

  @GetMapping("/settlement/{userId}")
  public ResponseEntity getSettlement(@PathVariable Long userId) {

    SettlementCommand settlementCommand = getSettlementUseCase.getSettlement(userId);
    GetSettlementResponse response = new GetSettlementResponse(settlementCommand, userId);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }

}
