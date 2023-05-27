package com.connect.accountApp.domain.user.application.service;

import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.in.GetSettlementRoommatesUseCase;
import com.connect.accountApp.domain.user.application.port.in.command.SendMoneyCommand;
import com.connect.accountApp.domain.user.application.port.out.GetRoommateSendMoneyPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserSendMoneyPort;
import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSettlementRoommatesService implements GetSettlementRoommatesUseCase {

  private final GetUserSendMoneyPort getUserSendMoneyPort;
  private final GetRoommateSendMoneyPort getRoommateSendMoneyPort;
  private final GetHouseholdPort getHouseholdPort;
  private final GetUserPort getUserPort;
  private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;

  @Override
  public SendMoneyCommand getSendMoney(Long userId) {

    User user = getUserPort.getUser(userId);
    Household household = getHouseholdPort.getHousehold(user.getHousehold().getHouseholdId());
    LocalDate householdSettlementDate = household.getHouseholdSettlementDate();

    //TODO : 날짜에 맞추기
    LocalDate pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), householdSettlementDate);


    TotalExpenseCommand userSendMoney = getUserSendMoneyPort.getUserSendMoney(userId,
        pastNearSettlementDate);

    List<RoommateSendMoneyCommand> roommateSendMoney = getRoommateSendMoneyPort.getRoommateSendMoney(
        household.getHouseholdId(), userId);

    log.info("[GetSendMoneyService] householdId : {}", household.getHouseholdId());
    int householdTotalExpense = getHouseholdTotalExpensePort.getHouseholdTotalExpense(
        household.getHouseholdId(),
        pastNearSettlementDate.atStartOfDay().minusMonths(1),
        pastNearSettlementDate.atStartOfDay());



    log.info("[GetSendMoneyService] roommateSendMoney.size() : {}", roommateSendMoney.size());
    return new SendMoneyCommand(pastNearSettlementDate.minusMonths(1), userSendMoney, roommateSendMoney,
        householdTotalExpense);
  }

  /**
   * 주어진 일자와 가까운 정산일을 반환한다.
   * @param date
   * @return 주어진 일자와 가장 가까운 정산일 (LocalDate)
   */
  private LocalDate getPastNearSettlementDate(LocalDate date, LocalDate householdSettlementDate) {

    if (date.getDayOfMonth() >= householdSettlementDate.getDayOfMonth()) {
      return LocalDate.of(date.getYear(), date.getMonth(), householdSettlementDate.getDayOfMonth());
    } else {
      // TODO : 30, 31 고려
      if(householdSettlementDate.getDayOfMonth()==31) {
        return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate.getDayOfMonth()-1);
      }
      return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate.getDayOfMonth());
    }

  }
}
