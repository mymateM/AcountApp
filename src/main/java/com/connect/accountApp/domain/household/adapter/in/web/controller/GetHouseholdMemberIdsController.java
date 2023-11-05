package com.connect.accountApp.domain.household.adapter.in.web.controller;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdMembersIdsUseCase;
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
@RequestMapping("/api/v1/household")
@RequiredArgsConstructor
public class GetHouseholdMemberIdsController {

    private final GetHouseholdMembersIdsUseCase getHouseholdMembersIdsUseCase;

    @GetMapping("/member/ids")
    public ResponseEntity getHouseholdMembersIds(@AuthenticationPrincipal UserDetails userDetails) {

        System.out.println(" = ");

        String userEmail = userDetails.getUsername();
        List<Long> householdMembersIds = getHouseholdMembersIdsUseCase.getHouseholdMembersIds(userEmail);

        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(householdMembersIds));
    }
}
