package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserSettlementRequest;
import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserSettlementRequest.UserSettlementRequest;
import com.connect.accountApp.domain.user.application.port.in.command.UpdateUserSettlementRatioUseCase;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateUserSettlementRatioService implements UpdateUserSettlementRatioUseCase {

    private final GetUserPort getUserPort;
    private final FindHouseholdUserListPort findHouseholdUserListPort;
    private final SaveUserPort saveUserPort;

    @Override
    public void updateUserSettlementRatio(String userEmail, UpdateUserSettlementRequest request) {

        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);

        List<UserSettlementRequest> newHouseholdMembers = request.getHouseholdMembers();

        List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(
                userWithHousehold.getHousehold().getHouseholdId());


        for (User member : householdMembers) {

            for (UserSettlementRequest userSettlementRequest : newHouseholdMembers) {
                if (Objects.equals(member.getUserId(), userSettlementRequest.getUserId())) {
                    member.updateUserSettlementRatio(userSettlementRequest.getUserSettlementRatio());
                }
            }
        }

        for (User member : householdMembers) {
            saveUserPort.save(member);
        }

    }
}
