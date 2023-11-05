package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdMembersIdsUseCase;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdMembersIdsService implements GetHouseholdMembersIdsUseCase {

    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;

    @Override
    public List<Long> getHouseholdMembersIds(String userEmail) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);
        Long householdId = userWithHousehold.getHousehold().getHouseholdId();

        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(householdId);

        List<Long> membersId = householdMembers.stream().map(householdMember -> householdMember.getUserId()).toList();

        return membersId;
    }
}
