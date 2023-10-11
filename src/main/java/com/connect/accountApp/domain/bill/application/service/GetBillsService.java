package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.in.GetBillsUseCase;
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
public class GetBillsService implements GetBillsUseCase {

  private final GetUserPort getUserPort;
  private final FindBillPort findBillPort;

  @Override
  public List<BillCommand> getBills(String userEmail, BillCategory billCategory) {

    User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
    Household household = userWithHousehold.getHousehold();
    return findBillPort.findBills(household.getHouseholdId(), billCategory);
  }
}
