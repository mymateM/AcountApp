package com.connect.accountApp.domain.bill.application.port.in;

import com.connect.accountApp.domain.bill.domain.model.Bill;

public interface GetBillUseCase {

    Bill getBill(Long billId);
}
