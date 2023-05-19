package com.connect.accountApp.domain.user.application.port.in;

import com.connect.accountApp.domain.user.application.port.in.command.SendMoneyCommand;

public interface GetSendMoneyUseCase {

  SendMoneyCommand getSendMoney(Long userId);

}
