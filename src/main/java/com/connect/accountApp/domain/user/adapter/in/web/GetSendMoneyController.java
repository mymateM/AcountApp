package com.connect.accountApp.domain.user.adapter.in.web;

import com.connect.accountApp.domain.user.adapter.in.web.response.SendMoneyResponse;
import com.connect.accountApp.domain.user.application.port.in.GetSendMoneyUseCase;
import com.connect.accountApp.domain.user.application.port.in.command.SendMoneyCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetSendMoneyController {

  private final GetSendMoneyUseCase getSendMoneyUseCase;

  @GetMapping("/send-money/{userId}")
  public ResponseEntity getSendMoney(@PathVariable Long userId) {

    SendMoneyCommand sendMoney = getSendMoneyUseCase.getSendMoney(userId);

    SendMoneyResponse response = new SendMoneyResponse(sendMoney);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));

  }


}
