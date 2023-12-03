package com.connect.accountApp.domain.settlement.application.port.in;

import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand;
import java.time.LocalDate;

public interface GetUserReportUseCase {

    UserReportCommand getUserReport(String userEmail, LocalDate startDate);
}
