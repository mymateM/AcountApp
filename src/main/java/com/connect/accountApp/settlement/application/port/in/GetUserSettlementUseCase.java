package com.connect.accountApp.settlement.application.port.in;

import com.connect.accountApp.settlement.application.port.in.command.UserSettlementCommand;
import java.time.LocalDate;

public interface GetUserSettlementUseCase {

  UserSettlementCommand getUserSettlement(String userEmail, LocalDate startDate, LocalDate endDate);

}
