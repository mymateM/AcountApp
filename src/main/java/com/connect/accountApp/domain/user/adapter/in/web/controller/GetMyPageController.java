package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.response.GetMyPageResponse;
import com.connect.accountApp.domain.user.adapter.in.web.response.GetUserDeviceTokenResponse;
import com.connect.accountApp.domain.user.application.port.in.GetMypageUseCase;
import com.connect.accountApp.domain.user.application.port.in.command.GetMyPageCommand;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GetMyPageController {

    private final GetMypageUseCase getMypageUseCase;


    @GetMapping("/mypage")
    public ResponseEntity getUserDeviceToken(@AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();

        GetMyPageCommand command = getMypageUseCase.getMyPage(userEmail);
        GetMyPageResponse response = new GetMyPageResponse(command);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }



}
