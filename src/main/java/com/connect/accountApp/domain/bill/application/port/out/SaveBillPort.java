package com.connect.accountApp.domain.bill.application.port.out;

import com.connect.accountApp.domain.bill.domain.model.Bill;

public interface SaveBillPort {

  Bill saveBill(Bill newBill);

}
