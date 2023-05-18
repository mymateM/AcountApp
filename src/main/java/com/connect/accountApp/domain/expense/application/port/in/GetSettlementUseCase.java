package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.application.port.in.command.SettlementCommand;

public interface GetSettlementUseCase {

  SettlementCommand getSettlement(Long userId);

}
