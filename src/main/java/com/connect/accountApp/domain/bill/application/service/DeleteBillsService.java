package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.application.port.in.DeleteBillsUseCase;
import com.connect.accountApp.domain.bill.application.port.out.DeleteBillPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBillsService implements DeleteBillsUseCase {

    private final DeleteBillPort deleteBillPort;
    @Override
    public void deleteBills(String userEmail, List<Long> billIds) {
        deleteBillPort.deleteBills(billIds);
    }
}
