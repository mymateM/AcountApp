package com.connect.accountApp.domain.household.application.port.out;

import com.connect.accountApp.domain.household.domain.model.Household;

public interface SaveHouseholdPort {

  void saveHousehold(Household household);

}
