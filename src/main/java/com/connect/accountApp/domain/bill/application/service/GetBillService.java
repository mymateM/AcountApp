package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.application.port.in.GetBillUseCase;
import com.connect.accountApp.domain.bill.application.port.out.FindBillPort;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBillService implements GetBillUseCase {

    private final FindBillPort findBillPort;

    @Override
    public Bill getBill(Long billId) {
        Bill bill = findBillPort.findBill(billId);
        return bill;
    }

}
