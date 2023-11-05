package com.connect.accountApp.domain.bill.adapter.in.web.controller;

import com.connect.accountApp.domain.bill.application.port.in.DeleteBillsUseCase;
import com.connect.accountApp.global.common.adapter.in.web.response.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DeleteBillsController {

    private final DeleteBillsUseCase deleteBillsUseCase;

    @DeleteMapping("/bills")
    public ResponseEntity deleteBills(@AuthenticationPrincipal UserDetails userDetails,
                                      @RequestParam("bill_id_list") List<Long> billIds) {

        String userEmail = userDetails.getUsername();

        deleteBillsUseCase.deleteBills(userEmail, billIds);
        return ResponseEntity.ok(SuccessResponse.create204NoContentResponse());
    }
}
