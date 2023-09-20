package com.connect.accountApp.global.common.adapter.in.web;

import com.connect.accountApp.global.common.adapter.in.web.request.FcmNotificationRequest;
import com.connect.accountApp.global.common.service.FcmNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test/notification")
@RequiredArgsConstructor
public class FcmNotificationController {

  private final FcmNotificationService fcmNotificationService;

  @PostMapping("/check")
  public String sendNotificationByToken(@RequestBody FcmNotificationRequest request) {
    return fcmNotificationService.sendNotificationByToken(request);
  }

}
