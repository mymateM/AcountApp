package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.expense.application.port.in.command.HomeCommand;

public interface GetHouseholdSettlementUseCase {

  HomeCommand getHouseholdSettlement(Long userId);

}
