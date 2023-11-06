package com.connect.accountApp.domain.user.adapter.in.web.controller;

import com.connect.accountApp.domain.user.adapter.in.web.response.GetBanksResponse;
import com.connect.accountApp.domain.user.domain.model.Bank;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GetBankController {

    @GetMapping("/banks")
    public ResponseEntity getBanks(@AuthenticationPrincipal UserDetails userDetails) {
        GetBanksResponse response = new GetBanksResponse(List.of(Bank.values()));
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }

}
