package com.connect.accountApp.settlement.application.port.in;

import com.connect.accountApp.settlement.application.port.in.command.HouseholdSettlementCommand;
import java.time.LocalDate;

public interface GetHouseholdSettlementUseCase {

  HouseholdSettlementCommand getRoommateSettlement(String userEmail, LocalDate startDate, LocalDate endDate);

}
