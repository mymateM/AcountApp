package com.connect.accountApp.domain.bill.adapter.in.web.controller;

import com.connect.accountApp.domain.bill.adapter.in.web.response.RecentBillCategoriesResponse;
import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.application.port.in.GetRecentBillCategoryUseCase;
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
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class GetRecentBillCategoryController {

    private final GetRecentBillCategoryUseCase getRecentBillCategoryUseCase;

    @GetMapping("/category")
    public ResponseEntity getBillsUseCase(@AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();
        List<RecentBillCategoryCommand> commands =
                getRecentBillCategoryUseCase.getRecentBillCategory(userEmail);

        RecentBillCategoriesResponse response = new RecentBillCategoriesResponse(commands);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }
}
