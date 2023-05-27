package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetMySettlementUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.MySettlementCommand;
import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserSendMoneyPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMySettlementService implements GetMySettlementUseCase {

  private final GetUserPort getUserPort;
  private final GetUserSendMoneyPort getUserSendMoneyPort;
  private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;

  @Override
  public MySettlementCommand getMySettlement(Long userId) {

    User user = getUserPort.getUser(userId);

    //TODO : Household household = user.getHousehold();
    Household household = user.getHousehold();

    LocalDate householdSettlementDate = household.getHouseholdSettlementDate();
    LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), householdSettlementDate);

    int householdTotalExpense = getHouseholdTotalExpensePort.getHouseholdTotalExpense(
        household.getHouseholdId(),
        pastNearSettlementDate.minusMonths(1).minusSeconds(1), pastNearSettlementDate);

    // TODO 매개변수 startTime, endTime 으로 변환
    TotalExpenseCommand command = getUserSendMoneyPort.getUserSendMoney(userId,
        pastNearSettlementDate.toLocalDate());

    return new MySettlementCommand(pastNearSettlementDate.minusMonths(1).toLocalDate(),
        householdTotalExpense, command);
  }


  /**
   * 주어진 일자와 가까운 정산일을 반환한다.
   * @param date
   * @return 주어진 일자와 가장 가까운 정산일 (LocalDate)
   */
  private LocalDateTime getPastNearSettlementDate(LocalDate date, LocalDate householdSettlementDate) {

    if (date.getDayOfMonth() >= householdSettlementDate.getDayOfMonth()) {
      return LocalDate.of(date.getYear(), date.getMonth(), householdSettlementDate.getDayOfMonth()).atStartOfDay();
    } else {
      // TODO : 30, 31 고려
      if(householdSettlementDate.getDayOfMonth()==31) {
        return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate.getDayOfMonth()-1).atStartOfDay();
      }
      return LocalDate.of(date.minusMonths(1).getYear(), date.minusMonths(1).getMonth(), householdSettlementDate.getDayOfMonth()).atStartOfDay();
    }

  }
}
