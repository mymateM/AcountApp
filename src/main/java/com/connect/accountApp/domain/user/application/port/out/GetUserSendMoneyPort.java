package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDate;

public interface GetUserSendMoneyPort {

  TotalExpenseCommand getUserSendMoney(Long userId, LocalDate date);

}
