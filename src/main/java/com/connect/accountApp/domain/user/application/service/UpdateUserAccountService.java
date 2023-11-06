package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.user.adapter.in.web.request.UpdateUserAccountRequest;
import com.connect.accountApp.domain.user.application.port.in.UpdateUserAccountUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.domain.model.Bank;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserAccountService implements UpdateUserAccountUseCase {

    private final GetUserPort getUserPort;
    private final SaveUserPort saveUserPort;

    @Override
    public void updateUserAccount(String userEmail, UpdateUserAccountRequest request) {

        // user 가져옴 계좌 수정하고 저장
        User user = getUserPort.findUser(userEmail);
        Bank userBank = Bank.valueOf(request.getAccountBank());
        user.updateUserAccount(userBank, request.getAccountNumber());

        saveUserPort.save(user);
    }
}
