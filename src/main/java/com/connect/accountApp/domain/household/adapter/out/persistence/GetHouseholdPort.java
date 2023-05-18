package com.connect.accountApp.domain.household.adapter.out.persistence;

import com.connect.accountApp.domain.household.domain.model.Household;

public interface GetHouseholdPort {

  Household getHousehold(Long householdId);

}
