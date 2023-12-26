package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.application.port.in.GetBillUseCase;
import com.connect.accountApp.domain.bill.application.port.out.FindBillPort;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBillService implements GetBillUseCase {

    private final FindBillPort findBillPort;
    private final GetUserPort getUserPort;

    @Override
    public Bill getBill(Long billId) {
        Bill bill = findBillPort.findBill(billId);
        return bill;
    }

    @Override
    public List<BillCommand> getBills(String userEmail, BillCategory billCategory) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Household household = userWithHousehold.getHousehold();
        return findBillPort.findBills(household.getHouseholdId(), billCategory);
    }

    @Override
    public List<RecentBillCategoryCommand> getRecentBillCategory(String userEmail) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Long householdId = userWithHousehold.getHousehold().getHouseholdId();
        return findBillPort.findRecentBills(householdId);
    }
}
