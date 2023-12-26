package com.connect.accountApp.domain.bill.adapter.in.web.controller;

import com.connect.accountApp.domain.bill.adapter.in.web.response.GetBillResponse;
import com.connect.accountApp.domain.bill.application.port.in.GetBillUseCase;
import com.connect.accountApp.domain.bill.domain.model.Bill;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GetBillController {

    private final GetBillUseCase getBillUseCase;

    @GetMapping("/bill/{bill_id}")
    public ResponseEntity getBillsUseCase(@AuthenticationPrincipal UserDetails userDetails,
                                          @PathVariable("bill_id") Long billId) {

        Bill bill = getBillUseCase.getBill(billId);

        GetBillResponse response = new GetBillResponse(bill);
        return ResponseEntity.ok(SuccessResponse.create200SuccessResponse(response));
    }
}
