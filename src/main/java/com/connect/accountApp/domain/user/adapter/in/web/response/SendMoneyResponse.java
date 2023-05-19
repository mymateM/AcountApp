package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.connect.accountApp.domain.user.application.port.in.command.RoommateCommand;
import com.connect.accountApp.domain.user.application.port.in.command.SendMoneyCommand;
import com.connect.accountApp.domain.user.application.port.in.command.UserCommand;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendMoneyResponse {

  private LocalDate year_month;
  private User user;
  private List<Roommate> room_mates = new ArrayList<>();

  public SendMoneyResponse(SendMoneyCommand command) {
    this.year_month = command.getYearMonth();
    this.user = new User(command.getUser());
    //TODO: 나머지 map 사용하여 매핑
    this.room_mates =  command.getRoommates().stream()
        .map(Roommate::new)
        .toList();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class User {

    private Long user_id;
    private String user_name;
    private int user_settlement;

    public User(UserCommand command) {
      this.user_id = command.getUserId();
      this.user_name = command.getUserName();
      this.user_settlement = command.getUserSettlement();
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class Roommate {

    private Long userId;
    private String user_name;
    private int user_ratio;
    private int user_settlement_ratio;
    private String user_account_bank;
    private String user_account;

    public Roommate(RoommateCommand command) {
      this.userId = command.getUserId();
      this.user_name = command.getUserName();
      this.user_ratio = command.getUserRatio();
      this.user_settlement_ratio = command.getUserSettlementRatio();
      this.user_account_bank = command.getUserAccountBank();
      this.user_account = command.getUserAccount();
    }
  }

}


