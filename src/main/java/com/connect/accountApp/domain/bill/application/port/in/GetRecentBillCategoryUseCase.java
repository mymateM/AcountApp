package com.connect.accountApp.domain.bill.application.port.in;

import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import java.util.List;

public interface GetRecentBillCategoryUseCase {

    List<RecentBillCategoryCommand> getRecentBillCategory(String userEmail);
}
