package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.application.port.in.GetUserAccountUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserAccountService implements GetUserAccountUseCase {

    private final GetUserPort getUserPort;

    @Override
    public User getUserAccount(String userEmail) {

       return getUserPort.findUser(userEmail);
    }
}
