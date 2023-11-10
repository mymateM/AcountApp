package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.household.application.port.in.GetHouseholdUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdService implements GetHouseholdUseCase {

    private final GetUserPort getUserPort;

    @Override
    public String getHouseholdName(String userEmail) {

        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);

        return userWithHousehold.getHousehold().getHouseholdName();
    }

    @Override
    public String getHouseholdInviteCode(String userEmail) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);

        return userWithHousehold.getHousehold().getInviteCode();
    }
}
