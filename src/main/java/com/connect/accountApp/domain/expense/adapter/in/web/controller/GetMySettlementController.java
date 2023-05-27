package com.connect.accountApp.domain.expense.adapter.in.web.controller;

import com.connect.accountApp.domain.expense.adapter.in.web.controller.response.GetMySettlementResponse;
import com.connect.accountApp.domain.expense.application.port.in.GetMySettlementUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.MySettlementCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetMySettlementController {


  private final GetMySettlementUseCase getMySettlementUseCase;


  @GetMapping("/settlement/me/{userId}")
  public ResponseEntity getMySettlement(@PathVariable Long userId) {

    MySettlementCommand settlementCommand = getMySettlementUseCase.getMySettlement(userId);
    GetMySettlementResponse response = new GetMySettlementResponse(settlementCommand);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
  }



}
