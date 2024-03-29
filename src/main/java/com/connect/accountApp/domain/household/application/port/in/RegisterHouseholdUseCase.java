package com.connect.accountApp.domain.household.application.port.in;

import com.connect.accountApp.domain.household.adapter.in.web.request.RegisterHouseholdRequest;

public interface RegisterHouseholdUseCase {

  String registerHousehold(String userEmail, RegisterHouseholdRequest request);

}
