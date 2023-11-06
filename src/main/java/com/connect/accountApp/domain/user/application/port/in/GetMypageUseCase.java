package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.application.port.in.command.GetMyPageCommand;

public interface GetMypageUseCase {

    GetMyPageCommand getMyPage(String userEmail);
}
