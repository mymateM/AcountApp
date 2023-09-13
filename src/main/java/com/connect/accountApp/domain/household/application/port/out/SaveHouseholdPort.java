package com.connect.accountApp.domain.household.application.port.out;

import com.connect.accountApp.domain.household.domain.model.Household;

public interface SaveHouseholdPort {

  Household saveHousehold(Household household);

}
