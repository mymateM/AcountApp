package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.response.UserAccountResponse;
import com.connect.accountApp.domain.user.application.port.in.GetUserAccountUseCase;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class GetUserAccountController {

    private final GetUserAccountUseCase getUserAccountUseCase;

    @GetMapping("/account")
    public ResponseEntity getUserAccount(@AuthenticationPrincipal UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        User user = getUserAccountUseCase.getUserAccount(userEmail);
        UserAccountResponse response = new UserAccountResponse(user);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }
}
