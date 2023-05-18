package com.connect.accountApp.domain.expense.adapter.in.web.controller.response;

import com.connect.accountApp.domain.expense.application.port.in.command.MemberCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SettlementCommand;
import com.connect.accountApp.domain.user.exception.UserNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetSettlementResponse {

  private LocalDate year_date;
  private User user;
  private List<User> room_mates = new ArrayList<>();

  public GetSettlementResponse(SettlementCommand command, Long userId) {
    this.year_date = command.getYearDate();
    this.user = findUser(command, userId);
    findRoomMates(command, userId).forEach(roomMate -> room_mates.add(new User(roomMate)));
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  private class User {

    private Long user_id;
    private String user_name;
    private int user_real_expense;
    private int user_ratio_expense;
    private int user_settlement;

    public User(MemberCommand command) {
      this.user_id = command.getUserId();
      this.user_name = command.getUserName();
      this.user_real_expense = command.getUserTotalExpense();
      this.user_ratio_expense = command.getUserRatioExpense();
      this.user_settlement = command.getUserSettlement();
    }
  }

  private User findUser(SettlementCommand command, Long userId) {
    return new User(command.getMembers().stream()
        .filter(memberCommand -> memberCommand.getUserId().equals(userId))
        .findFirst()
        .orElseThrow(() -> new UserNotFoundException("[userId] : " + userId + " 사용자가 존재하지 않습니다.")));
  }

  private List<MemberCommand> findRoomMates(SettlementCommand command, Long userId) {
    return command.getMembers().stream()
        .filter(memberCommand -> !memberCommand.getUserId().equals(userId)).toList();
  }

}
