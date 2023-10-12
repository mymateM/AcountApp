package com.connect.accountApp.domain.bill.application.port.in;

import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;

public interface RegisterBillUseCase {

  Long registerBill(String userEmail, RegisterBillRequest request);

}
