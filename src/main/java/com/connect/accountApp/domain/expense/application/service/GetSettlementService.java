package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetSettlementUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.SettlementCommand;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
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
  private final FindHouseholdUserListPort findHouseholdUserListPort;


  @Override
  public SettlementCommand getSettlement(Long userId) {

    User user = getUserPort.getUser(userId);
    Household household = getHouseholdPort.getHousehold(user.getHousehold().getHouseholdId());
    LocalDate householdSettlementDate = household.getHouseholdSettlementDate();

    //TODO : 날짜에 맞추기
    LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), householdSettlementDate);

    List<TotalExpenseCommand> totalExpenseList = getTotalExpensePort.getTotalExpense(
        household.getHouseholdId(), pastNearSettlementDate.minusMonths(1).minusSeconds(1), pastNearSettlementDate);

    List<User> householdUsers = findHouseholdUserListPort.findHouseholdUserList(household);
    List<TotalExpenseCommand> totalExpenseCommands = setDefaultTotalExpenseCommands(householdUsers);
    updateTotalExpense(totalExpenseList, totalExpenseCommands);

   return new SettlementCommand(pastNearSettlementDate.minusMonths(1).toLocalDate(), totalExpenseCommands);

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

  /**
   * User 객체를 사용해, TotalExpenseCommand 의 기본값을 설정하는 함수
   * @param householdUsers
   * @return List<TotalExpenseCommand> : TotalExpenseCommand 리스트 객체
   */
  private List<TotalExpenseCommand> setDefaultTotalExpenseCommands(List<User> householdUsers) {
    List<TotalExpenseCommand> totalExpenseCommands = householdUsers.stream()
        .map(
            householdUser -> new TotalExpenseCommand(householdUser.getUserId(),
                householdUser.getUserName(), 0, householdUser.getUserRatio())
        ).toList();
    return totalExpenseCommands;
  }

  /**
   * TotalExpenseCommand 의 값에서 totalExpense 를 업데이트 함
   * @param totalExpenseList 실제 쿼리를 날려 totalExpense 값을 모아둔 리스트
   * @param totalExpenseCommands totalExpense 의 값이 모두 0으로 설정된 리스트
   */
  private void updateTotalExpense(List<TotalExpenseCommand> totalExpenseList,
      List<TotalExpenseCommand> totalExpenseCommands) {
    totalExpenseCommands
        .forEach(
            totalExpenseCommand -> {
              totalExpenseList.forEach(
                  totalExpense -> {
                    if (totalExpense.getUserId().equals(totalExpenseCommand.getUserId())){
                      totalExpenseCommand.updateUserTotalExpense(totalExpense.getUserTotalExpense());
                    }
                  }
              );
            }
        );
  }
}
