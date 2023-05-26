package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalExpenseWithTitleCommand extends TotalExpenseCommand {

  private String userTitleName;
  private LocalDateTime createdAt;
  private String userTitleImgUrl;

  public TotalExpenseWithTitleCommand(TotalExpenseCommand command, LocalDateTime createdAt, String userTitleName, String userTitleImgUrl) {
    super(command.getUserId(), command.getUserName(), command.getUserTotalExpense(), command.getUserRatio());
    this.userTitleName = userTitleName;
    this.createdAt = createdAt;
    this.userTitleImgUrl = userTitleImgUrl;
  }
}
