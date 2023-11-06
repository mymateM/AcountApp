package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.application.port.in.GetMypageUseCase;
import com.connect.accountApp.domain.user.application.port.in.command.GetMyPageCommand;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMypageService implements GetMypageUseCase {

    private final GetUserPort getUserPort;

    @Override
    public GetMyPageCommand getMyPage(String userEmail) {
        User userWithHousehold = getUserPort.findUserWithHousehold(userEmail);

        return new GetMyPageCommand(userWithHousehold);
    }
}
