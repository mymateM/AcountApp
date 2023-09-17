package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.application.port.in.AcceptingInvitiation;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class UserEnterHouseholdController {

  private final AcceptingInvitiation acceptingInvitiation;
//  private final NotiAcceptingInvitationUseCase notiAcceptingInvitationUseCase;

  @GetMapping("/enter/{invite_code}")
  public ResponseEntity userEnterHousehold(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable("invite_code") String householdInviteCode) {



    String userEmail = userDetails.getUsername();
    acceptingInvitiation.userEnterHousehold(userEmail, householdInviteCode);

    return ResponseEntity.ok(SuccessResponse.create200SuccessResponse());
  }

}
