package com.connect.accountApp.domain.bill.application.port.in;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.util.List;

public interface GetBillsUseCase {

  List<BillCommand> getBills(String userEmail, BillCategory billCategory);

}
