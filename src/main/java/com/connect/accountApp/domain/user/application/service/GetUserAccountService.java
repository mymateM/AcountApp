package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdUseCase;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.user.application.port.in.GetUserAccountUseCase;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserAccountService implements GetUserAccountUseCase {

    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;

    @Override
    public User getUserAccount(String userEmail) {

       return getUserPort.findUser(userEmail);
    }

    @Override
    public List<User> getMembersAccount(String userEmail) {
        System.out.println("userEmail = " + userEmail);
        User user = getUserPort.findUserWithHousehold(userEmail);
        System.out.println("user = " + user.getUserId());
        System.out.println("user = " + user.getUserAccount());
        System.out.println("user = " + user.getUserNickname());
        System.out.println("user = " + user.getHousehold());

        System.out.println("user.getHousehold()1 = " + user.getHousehold());
//        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers();

//        System.out.println("householdMembers.size() = " + householdMembers.size());
        return findHouseholdUserListPort.findHouseholdMembers(user.getHousehold().getHouseholdId());
    }
}
