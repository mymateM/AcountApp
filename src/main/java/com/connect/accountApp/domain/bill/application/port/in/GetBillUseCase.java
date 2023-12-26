package com.connect.accountApp.domain.bill.application.port.in;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.util.List;

public interface GetBillUseCase {

    Bill getBill(Long billId);

    List<BillCommand> getBills(String userEmail, BillCategory billCategory);

    List<RecentBillCategoryCommand> getRecentBillCategory(String userEmail);
}
