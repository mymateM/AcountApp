package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.AcceptingInvitiation;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEnterHouseholdService implements AcceptingInvitiation {

  private final GetUserPort getUserPort;
  private final GetHouseholdPort getHouseholdPort;
  private final FindHouseholdUserListPort householdUserListPort; //todo list -> 삭제 포트이름 바꾸기

  private final SaveUserPort saveUserPort;


  @Override
  public void userEnterHousehold(String userEmail, String householdInviteCode) {

    User user = getUserPort.findUser(userEmail);
    Household household = getHouseholdPort.findHousehold(householdInviteCode); //todo 이미 존재하는 사용자면 ㄴㄴ

    registerUserToHousehold(user, household);
    reCalculateHouseholdMembersSettlementRatio(household);
  }

  private void registerUserToHousehold(User user, Household household) {
    user.registerUserToHousehold(household);
    saveUserPort.save(user);
  }

  private void reCalculateHouseholdMembersSettlementRatio(Household household) {
    
    List<User> householdMembers = householdUserListPort.findHouseholdMembers(household.getHouseholdId());

    int settlementRatio = 100 / (householdMembers.size());
    householdMembers.forEach(member -> updateUserSettlementRatio(member, settlementRatio));
  }

  private void updateUserSettlementRatio(User user, int settlementRatio) {
    user.updateUserSettlementRatio(settlementRatio);
    saveUserPort.save(user);
  }

}
