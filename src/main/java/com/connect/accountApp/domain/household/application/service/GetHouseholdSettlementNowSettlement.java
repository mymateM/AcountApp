package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.expense.application.port.in.command.HomeCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MemberNowCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MembersNowCommand;
import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.adapter.out.persistence.GetHouseholdPort;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.HouseholdNowCommand;
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
public class GetHouseholdSettlementNowSettlement implements GetHouseholdSettlementUseCase {

  private final GetUserPort getUserPort;
  private final GetHouseholdPort getHouseholdPort;
  private final GetTotalExpensePort getTotalExpensePort;
  private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;
  private final FindHouseholdUserListPort findHouseholdUserListPort;

  @Override
  public HomeCommand getHouseholdSettlement(Long userId) {

    // 사용자 구하기
    User user = getUserPort.getUser(userId);

    // 집 정보 구하기
    Household household = getHouseholdPort.getHousehold(user.getHousehold().getHouseholdId());




    LocalDate householdSettlementDate = household.getHouseholdSettlementDate();
    LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), householdSettlementDate);

    // user가 쓴 것이 없음

    List<TotalExpenseCommand> totalExpenseList =
        getTotalExpensePort.getTotalExpense(household.getHouseholdId(), pastNearSettlementDate, LocalDateTime.now());

    List<User> householdUsers = findHouseholdUserListPort.findHouseholdUserList(household);

    List<TotalExpenseCommand> totalExpenseCommands = householdUsers.stream()
        .map(
            householdUser -> new TotalExpenseCommand(householdUser.getUserId(),
                householdUser.getUserName(), 0, householdUser.getUserRatio())
        ).toList();

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

    List<MemberNowCommand> commands = totalExpenseCommands.stream().map(
        command -> new MemberNowCommand(command, household.getHouseholdBudget())).toList();


    MembersNowCommand membersNowCommand = new MembersNowCommand(userId, commands);

    // 현재
    LocalDateTime nowStartTime = pastNearSettlementDate.toLocalDate().atStartOfDay();
    LocalDateTime nowEndTime = LocalDate.now().atStartOfDay().plusDays(1).minusSeconds(1);

    // 과거
    LocalDateTime previousStartTime = nowStartTime.minusMonths(1);
    LocalDateTime previousEndTime = nowEndTime.minusMonths(1);

    // 현재 총 가격
    int nowTotalExpense = getHouseholdTotalExpensePort.getHouseholdTotalExpense(
        household.getHouseholdId(), nowStartTime, nowEndTime);

    int previousTotalExpense = getHouseholdTotalExpensePort.getHouseholdTotalExpense(
        household.getHouseholdId(),
        previousStartTime, previousEndTime);

    HouseholdNowCommand householdNowCommand = new HouseholdNowCommand(household,
        pastNearSettlementDate.toLocalDate(), nowTotalExpense, previousTotalExpense);

    return new HomeCommand(membersNowCommand, householdNowCommand);
  }

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
