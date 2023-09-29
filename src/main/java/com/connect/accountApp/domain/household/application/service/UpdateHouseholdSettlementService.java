package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.UpdateHouseholdSettlementUseCase;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateHouseholdSettlementService implements UpdateHouseholdSettlementUseCase {

  private final SaveHouseholdPort saveHouseholdPort;
  private final GetUserPort getUserPort;

  @Override
  public BigDecimal updateHouseholdSettlement(String userEmail, BigDecimal budgetAmount) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    Household household = user.getHousehold();

    household.updateHouseholdBudget(budgetAmount);
    household.setSettlementWillBeUpdatedToTrue();
    saveHouseholdPort.saveHousehold(household);

    return household.getHouseholdBudget();
  }
}
