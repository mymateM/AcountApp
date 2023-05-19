package com.connect.accountApp.domain.user.application.port.out;

import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import java.util.List;

public interface GetRoommateSendMoneyPort {

  List<RoommateSendMoneyCommand> getRoommateSendMoney(Long householdId, Long userId);

}
