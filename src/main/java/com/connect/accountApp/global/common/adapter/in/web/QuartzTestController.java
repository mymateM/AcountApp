package com.connect.accountApp.global.common.adapter.in.web;

import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import com.connect.accountApp.global.common.application.port.in.QuartzTestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test/")
@RequiredArgsConstructor
public class QuartzTestController {

  private final QuartzTestUseCase quartzTestUseCase;

  @GetMapping("/quartz")
  public ResponseEntity getPracticeResponse1(@RequestParam("second") int second) {

    quartzTestUseCase.changeScheduleTime(second);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }

  @GetMapping("/register-settlement-date")
  public ResponseEntity addAlarmSettlementDate(@RequestParam Integer dayOfMonth, @RequestParam Long householdId) {

    quartzTestUseCase.initSchedule(dayOfMonth, householdId);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }

}
