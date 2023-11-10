package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.household.application.port.in.command.GetHouseholdHomeCommand;
import com.connect.accountApp.domain.household.application.port.in.command.GetUserHomeCommand;

public interface GetHouseholdHomeUseCase {

    GetHouseholdHomeCommand getHouseholdHome(String userEmail);

    GetUserHomeCommand getUserHome(String userEmail);


}
