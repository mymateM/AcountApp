package com.connect.accountApp.domain.expense.application.port.in.command;

import com.connect.accountApp.domain.user.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembersNowCommand {

  private MemberNowCommand userNowCommand;
  private List<MemberNowCommand> roomMateNowCommands = new ArrayList<>();

  public MembersNowCommand(Long userId, List<MemberNowCommand> commands) {
    List<MemberNowCommand> commandArrayList = new ArrayList<>(commands);
    MemberNowCommand nowUser = findNowUser(commandArrayList, userId);
    commandArrayList.remove(nowUser);
    this.userNowCommand = nowUser;
    this.roomMateNowCommands = commandArrayList;
  }

  private MemberNowCommand findNowUser(List<MemberNowCommand> commands, Long userId) {
    return commands.stream()
        .filter(memberNowCommand -> memberNowCommand.getUserId().equals(userId)).findFirst()
        .orElseThrow(() -> new UserNotFoundException("[userId] : " + userId + " 사용자가 존재하지 않습니다."));
  }
}
