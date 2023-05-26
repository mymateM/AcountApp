package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetSettlementUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.SettlementCommand;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.adapter.out.persistence.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSettlementService implements GetSettlementUseCase {

  private final GetUserPort getUserPort;
  private final GetHouseholdPort getHouseholdPort;
  private final GetTotalExpensePort getTotalExpensePort;


  @Override
  public SettlementCommand getSettlement(Long userId) {

    User user = getUserPort.getUser(userId);
    Household household = getHouseholdPort.getHousehold(user.getHousehold().getHouseholdId());
    LocalDate householdSettlementDate = household.getHouseholdSettlementDate();

    //TODO : 날짜에 맞추기
    LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), householdSettlementDate);

    List<TotalExpenseCommand> totalExpenseList = getTotalExpensePort.getTotalExpense(
        household.getHouseholdId(), pastNearSettlementDate.plusDays(1).minusSeconds(1), pastNearSettlementDate);


   return new SettlementCommand(household.getHouseholdSettlementDate(), totalExpenseList);

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
