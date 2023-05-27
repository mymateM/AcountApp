package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.application.port.in.command.MySettlementCommand;

public interface GetMySettlementUseCase {

  MySettlementCommand getMySettlement(Long userId);

}
