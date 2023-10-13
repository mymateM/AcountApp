package com.connect.accountApp.domain.settlement.application.port.in;

import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand;
import java.time.LocalDate;

public interface GetHouseholdReportUseCase {

  HouseholdReportCommand getHouseholdReport(String userEmail, LocalDate reportStartDate);

}
