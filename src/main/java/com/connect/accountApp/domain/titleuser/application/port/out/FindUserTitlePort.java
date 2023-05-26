package com.connect.accountApp.domain.titleuser.application.port.out;

import com.connect.accountApp.domain.titleuser.application.port.out.command.UserTitleCommand;

public interface FindUserTitlePort {

  UserTitleCommand findUserTitle(Long userId);

}
