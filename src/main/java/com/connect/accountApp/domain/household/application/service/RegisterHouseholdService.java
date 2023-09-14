package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.adapter.in.web.request.RegisterHouseholdRequest;
import com.connect.accountApp.domain.household.application.port.in.RegisterHouseholdUseCase;
import com.connect.accountApp.domain.household.application.port.out.SaveHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterHouseholdService implements RegisterHouseholdUseCase {

  private final SaveHouseholdPort saveHouseholdPort;
  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;

  @Override
  public String registerHousehold(String userEmail, RegisterHouseholdRequest request) {

    Household defaultHousehold = createDefaultHouseholdByRequest(request);
    Household savedHousehold = saveHouseholdPort.saveHousehold(defaultHousehold);

    User user = getUserPort.findUser(userEmail);
    registerUserToHousehold(user, savedHousehold);

    return savedHousehold.getInviteCode();
  }

  private Household createDefaultHouseholdByRequest(RegisterHouseholdRequest request) {

    return createDefaultHousehold(request.getHouseholdName(),
        request.getHouseholdSettlementDayOfMonth(),
        request.getHouseholdBudget().getAmount(),
        request.getHouseholdBudget().getAllowanceRatio());
  }

  private Household createDefaultHousehold(String householdName, Integer settlementDayOfMonth, BigDecimal amount, Integer settlementAllowanceRatio) {

    String inviteCode = createInviteCodeOfHousehold();

    return Household.builder()
        .householdName(householdName)
        .householdSettlementDayOfMonth(settlementDayOfMonth)
        .householdSettlementDate(LocalDateTime.now().toLocalDate())
        .householdBudget(amount)
        .householdBudgetAllowanceRatio(settlementAllowanceRatio)
        .householdAccept(false)
        .inviteCode(inviteCode)
        .build();
  }


  private void registerUserToHousehold(User user, Household household) {
    user.registerUserToHousehold(household);
    user.updateUserSettlementRatio(100);
    saveUserPort.save(user);
  }

  private String createInviteCodeOfHousehold() { // todo 중복 문제
    return UUID.randomUUID().toString();
  }


}
