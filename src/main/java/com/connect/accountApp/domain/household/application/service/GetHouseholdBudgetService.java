package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdBudgetUseCase;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdBudgetService implements GetHouseholdBudgetUseCase {

    private final GetHouseholdPort getHouseholdPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;

    @Override
    public Household getHouseholdBudget(String userEmail) {
        Long householdId = findHouseholdUserListPort.findHouseholdId(userEmail);
        Household household = getHouseholdPort.getHousehold(householdId);
        return household;
    }
}
