package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.application.port.in.GetRecentBillCategoryUseCase;
import com.connect.accountApp.domain.bill.application.port.out.FindBillPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRecentBillCategoryService implements GetRecentBillCategoryUseCase {

    private final GetUserPort getUserPort;
    private final FindBillPort findBillPort;


    @Override
    public List<RecentBillCategoryCommand> getRecentBillCategory(String userEmail) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Long householdId = userWithHousehold.getHousehold().getHouseholdId();

        return findBillPort.findRecentBills(householdId);
    }
}
