package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;

import com.connect.accountApp.domain.expense.application.port.in.command.MemberCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MySettlementCommand;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMySettlementResponse {

  private int expense_total;
  private LocalDate year_month;
  private User user;


  public GetMySettlementResponse(MySettlementCommand command) {
    this.expense_total = command.getHouseholdTotalExpense();
    this.year_month = command.getYearDate().plusMonths(1);
    this.user = new User(command.getUser());
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class User {

    private Long user_id;
    private int user_ratio;
    private int user_real_expense;
    private int user_ratio_expense;
    private Boolean is_send;
    private int user_settlement;

    public User(MemberCommand command) {
      this.user_id = command.getUserId();
      this.user_ratio = command.getUserRatio();
      this.user_real_expense = command.getUserTotalExpense();
      this.user_ratio_expense = command.getUserRatioExpense();
      this.is_send = isSend(command.getUserSettlement());
      this.user_settlement = Math.abs(command.getUserSettlement());
    }

  }

  private Boolean isSend(int userSettlement) {
    return userSettlement > 0;
  }
}
