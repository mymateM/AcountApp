package com.connect.accountApp.domain.household.application.port.in;

import java.util.List;

public interface GetHouseholdMembersIdsUseCase {

    List<Long> getHouseholdMembersIds(String userEmail);
}
