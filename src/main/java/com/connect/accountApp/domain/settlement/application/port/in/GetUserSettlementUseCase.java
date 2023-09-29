package com.connect.accountApp.domain.settlement.application.port.in;

import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementWithHouseholdTotalExpenseCommand;
import java.time.LocalDate;

public interface GetUserSettlementUseCase {

  UserSettlementWithHouseholdTotalExpenseCommand getUserSettlement(String userEmail, LocalDate startDate, LocalDate endDate);

}
