package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.adapter.in.web.response.GetMembersSettlementRatioResponse;
import com.connect.accountApp.domain.household.application.port.in.GetMembersSettlementRatioUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.GetMembersSettlementCommand;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMembersSettlementRatioService implements GetMembersSettlementRatioUseCase {

  private final GetUserPort getUserPort;
  private final FindHouseholdUserListPort findHouseholdUserListPort;

  @Override
  public List<GetMembersSettlementCommand> getMembersSettlementRatio(String userEmail) {

    List<User> householdMembers = getMembersInUsersHousehold(userEmail);

    return getGetMembersSettlementCommandsMappedToUsers(householdMembers);
  }


  private List<User> getMembersInUsersHousehold(String userEmail) {
    User user = getUserPort.findUserWithHousehold(userEmail);
    List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(user.getHousehold().getHouseholdId());
    return householdMembers;
  }

  private List<GetMembersSettlementCommand> getGetMembersSettlementCommandsMappedToUsers(List<User> users) {

    return users.stream().map(
            householdMember -> new GetMembersSettlementCommand(
                householdMember.getUserId(),
                householdMember.getUserImgUrl(),
                householdMember.getUserNickname(),
                householdMember.getUserRatio())).toList();
  }
}
