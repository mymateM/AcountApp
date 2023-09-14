package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.UserEnterHouseholdUseCase;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEnterHouseholdService implements UserEnterHouseholdUseCase {

  private final GetUserPort getUserPort;
  private final GetHouseholdPort getHouseholdPort;

  private final SaveUserPort saveUserPort;


  @Override
  public void userEnterHousehold(String userEmail, String householdInviteCode) {

    /*
    초대 코드를 입력하면,
  가구에 입력한 사용자가 등록됨
  비율이 다시 산정됨
  기존에 있던 사람들에게 알림이 감
     */

    User user = getUserPort.findUser(userEmail);
    Household household = getHouseholdPort.findHousehold(householdInviteCode);

    registerUserToHousehold(user, household);

  }

  private void registerUserToHousehold(User user, Household household) {
    user.registerUserToHousehold(household);
    saveUserPort.save(user);
  }

}
