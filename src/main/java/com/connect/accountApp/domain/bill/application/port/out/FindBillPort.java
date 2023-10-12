package com.connect.accountApp.domain.bill.application.port.out;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.util.List;

public interface FindBillPort {

  List<BillCommand> findBills(Long householdId, BillCategory billCategory);

  Bill findBill(Long billId);

}
