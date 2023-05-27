package com.connect.accountApp.domain.household.application.service;

import com.connect.accountApp.domain.expense.application.port.in.command.HomeCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MemberNowCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.MembersNowCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.TotalExpenseWithTitleCommand;
import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.household.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.domain.household.application.port.in.command.HouseholdNowCommand;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.titleuser.application.port.out.FindUserTitlePort;
import com.connect.accountApp.domain.titleuser.application.port.out.command.UserTitleCommand;
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
  private final FindUserTitlePort findUserTitlePort;

  @Override
  public HomeCommand getHouseholdSettlement(Long userId) {

    User user = getUserPort.getUser(userId);
    Household household = getHouseholdPort.getHousehold(user.getHousehold().getHouseholdId());

    LocalDateTime pastNearSettlementDate = getPastNearSettlementDate(LocalDate.now(), household.getHouseholdSettlementDate());
    List<TotalExpenseCommand> totalExpenseList = getTotalExpensePort.getTotalExpense(household.getHouseholdId(), pastNearSettlementDate, LocalDateTime.now());
    List<User> householdUsers = findHouseholdUserListPort.findHouseholdUserList(household);
    List<TotalExpenseCommand> totalExpenseCommands = setDefaultTotalExpenseCommands(householdUsers);
    updateTotalExpense(totalExpenseList, totalExpenseCommands);

    //TODO title이 없을 때 처리
    List<TotalExpenseWithTitleCommand> totalExpenseWithTitleCommands = getTotalExpenseWithTitleCommands(
        totalExpenseCommands);

    MembersNowCommand membersNowCommand = createMembersNowCommand(userId, household, totalExpenseWithTitleCommands);

    HouseholdNowCommand householdNowCommand = new HouseholdNowCommand(household,
        pastNearSettlementDate.toLocalDate(), getHouseholdNowTotalExpense(pastNearSettlementDate,
        household.getHouseholdId()), getHouseholdPreviousTotalExpense(pastNearSettlementDate,
        household.getHouseholdId()));

    return new HomeCommand(membersNowCommand, householdNowCommand);
  }

  /**
   * title 할당
   * @param totalExpenseCommands
   * @return
   */
  private List<TotalExpenseWithTitleCommand> getTotalExpenseWithTitleCommands(
      List<TotalExpenseCommand> totalExpenseCommands) {
    List<TotalExpenseWithTitleCommand> totalExpenseWithTitleCommands = totalExpenseCommands.stream()
        .map(
            command -> {
              UserTitleCommand userTitleCommand = findUserTitlePort.findUserTitle(
                  command.getUserId());

              return new TotalExpenseWithTitleCommand(command, userTitleCommand.getCreatedAt(),
                  userTitleCommand.getTitleName(), userTitleCommand.getTitleImg());
            }
        ).toList();
    return totalExpenseWithTitleCommands;
  }

  /**
   * 가까운 정산일 부터 현재까지 쓴 돈을 반환하는 함수
   * @param pastNearSettlementDate : 가까운 정산일
   * @param householdId : 가구 아이디
   * @return int 가까운 정산일 부터 현재까지 쓴 돈
   */
  private int getHouseholdNowTotalExpense(LocalDateTime pastNearSettlementDate, Long householdId) {
    LocalDateTime nowStartTime = pastNearSettlementDate.toLocalDate().atStartOfDay();
    LocalDateTime nowEndTime = LocalDate.now().atStartOfDay().plusDays(1).minusSeconds(1);

    return getHouseholdTotalExpensePort.getHouseholdTotalExpense(householdId, nowStartTime, nowEndTime);
  }

  /**
   * 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈을 반환하는 함수
   * @param pastNearSettlementDate : 가까운 정산일
   * @param householdId : 가구 아이디
   * @return int 저번달 정산일 부터 지금으로부터 한달 전까지의 쓴 돈
   */
  private int getHouseholdPreviousTotalExpense(LocalDateTime pastNearSettlementDate, Long householdId) {
    LocalDateTime previousStartTime = pastNearSettlementDate.toLocalDate().atStartOfDay().minusMonths(1);
    LocalDateTime previousEndTime = LocalDate.now().atStartOfDay().plusDays(1).minusSeconds(1).minusMonths(1);

    return getHouseholdTotalExpensePort.getHouseholdTotalExpense(householdId, previousStartTime, previousEndTime);
  }

  /**
   * MembersNowCommand 생성 함수
   * @param userId 사용자 아이디
   * @param household 가구 아이디
   * @param totalExpenseCommands 값이 설정된 totalExpenseCommand 리스트
   * @return MembersNowCommand
   */
  private MembersNowCommand createMembersNowCommand(Long userId, Household household,
      List<TotalExpenseWithTitleCommand> totalExpenseCommands) {
    List<MemberNowCommand> commands = totalExpenseCommands.stream().map(
        command -> new MemberNowCommand(command, household.getHouseholdBudget())).toList();

    MembersNowCommand membersNowCommand = new MembersNowCommand(userId, commands);
    return membersNowCommand;
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
   * 현재 날짜에서 가장 가까운 정산일을 가져오는 함수
   * @param date
   * @param householdSettlementDate
   * @return LocalDateTime : 현재와 가장 가까운 정산일
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
