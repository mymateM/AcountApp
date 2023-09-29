package com.connect.accountApp.domain.household.application.port.in;

import java.math.BigDecimal;

public interface UpdateHouseholdSettlementUseCase {

  BigDecimal updateHouseholdSettlement(String userEmail, BigDecimal budgetAmount);

}
