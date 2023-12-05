package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.household.domain.model.Household;

public interface GetHouseholdBudgetUseCase {

    Household getHouseholdBudget(String userEmail);
}
