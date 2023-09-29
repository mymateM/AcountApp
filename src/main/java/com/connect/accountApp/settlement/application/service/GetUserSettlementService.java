package com.connect.accountApp.settlement.application.service;

import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.settlement.application.port.in.GetUserSettlementUseCase;
import com.connect.accountApp.settlement.application.port.in.command.UserSettlementWithHouseholdTotalExpenseCommand;
import com.connect.accountApp.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.settlement.application.port.out.command.ExpenseOfHouseholdCommand;
import com.connect.accountApp.settlement.application.port.out.command.ExpenseOfHouseholdCommand.ExpenseRatioOfUser;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserSettlementService implements GetUserSettlementUseCase {

  private final GetUserPort getUserPort;
  private final FindSettlementPort findSettlementPort;

  @Override
  public UserSettlementWithHouseholdTotalExpenseCommand getUserSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

    User user = getUserPort.findUserWithHousehold(userEmail);

    BigDecimal userRealTotalExpense = getUserTotalRealExpense(userEmail, startDate, endDate);

    List<ExpenseOfHouseholdCommand> expenseOfHouseholdCommands = findSettlementPort.findHouseholdExpenses(user.getHousehold().getHouseholdId(), startDate, endDate);

    List<ExpenseOfHouseholdCommand> filteredExpenseOfHouseholdCommands = filterExpenseOfHouseholdCommandIncludingUser(expenseOfHouseholdCommands, user);

    BigDecimal userRatioExpense = getUserRatioExpense(filteredExpenseOfHouseholdCommands, user);

    BigDecimal householdTotalExpense = getHouseholdTotalExpense(expenseOfHouseholdCommands);

    return new UserSettlementWithHouseholdTotalExpenseCommand(householdTotalExpense, userRealTotalExpense, userRatioExpense, user);
  }

  /**
   * 사용자의 실제 지출의 총 합을 반환하는 함수
   * @param userEmail user의 이메일 값
   * @param from 지출 소비 시작 날짜
   * @param to 지출 소비 마지막 날짜
   * @return user의 from에서 to까지 실제 지출의 총 합을 반환
   */
  private BigDecimal getUserTotalRealExpense(String userEmail, LocalDate from, LocalDate to) {
    List<BigDecimal> userRealExpenses = findSettlementPort.findUserRealExpense(userEmail, from, to);
    return userRealExpenses.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * 사용자가 지출한 소비 내역만 반환
   * @param commands ExpenseOfHouseholdCommand 리스트 객체
   * @param user 포함되어야하는 사용자
   * @return user를 포함하는 ExpenseOfHouseholdCommand 객체 리스트
   */
  private List<ExpenseOfHouseholdCommand> filterExpenseOfHouseholdCommandIncludingUser(List<ExpenseOfHouseholdCommand> commands, User user) {

    return commands.stream()
        .filter(expenseOfHouseholdCommand -> expenseOfHouseholdCommand
            .getExpenseRatioOfUsers()
            .stream().anyMatch(
                expenseRatioOfUser -> expenseRatioOfUser.getUserId().equals(user.getUserId())))
        .toList();
  }

  /**
   * 가구 전체의 총 지출 반환
   * @param expenseOfHouseholdCommands (어떤 일자 범위 내의) 지출 내역 리스트
   * @return expenseOfHouseholdCommands 내의 모든 지출(expenseAmount)을 더하여 반환
   */
  private BigDecimal getHouseholdTotalExpense(List<ExpenseOfHouseholdCommand> expenseOfHouseholdCommands) {
    return expenseOfHouseholdCommands.stream()
        .map(ExpenseOfHouseholdCommand::getExpenseAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * 주어진 사용자의 비율에 따른 정산 값 계산
   * @param filteredExpenseOfHouseholdCommands 각 지출에 대한 소비 비율이 들어있는 객체
   * @param user 사용자
   * @return 주어진 사용자의 비율에 따른 정산 값의 총합 반환
   */
  private BigDecimal getUserRatioExpense(
      List<ExpenseOfHouseholdCommand> filteredExpenseOfHouseholdCommands, User user) {

    return filteredExpenseOfHouseholdCommands.stream()
        .map(
            expenseOfHouseholdCommand -> {
              BigDecimal userRealRatio = getUserRealRatio(expenseOfHouseholdCommand, user);

              return expenseOfHouseholdCommand.getExpenseAmount().multiply(userRealRatio);
            }
        ).reduce(BigDecimal.ZERO, BigDecimal::add);

  }

  /**
   * 지출한 가구원들 사이에서 사용자가 실제 담당해야할 비율 계산
   * @return 사용자가 실제 담당해야할 비율 반환
   */
  private BigDecimal getUserRealRatio(ExpenseOfHouseholdCommand expenseOfHouseholdCommand, User user) {

    List<ExpenseRatioOfUser> expenseRatioOfUsers = expenseOfHouseholdCommand.getExpenseRatioOfUsers();

    int ratioSumOfUsers = expenseRatioOfUsers.stream()
        .mapToInt(ExpenseRatioOfUser::getUserExpenseRatio)
        .sum();

    return BigDecimal.valueOf(user.getUserRatio() / (double) ratioSumOfUsers);
  }

}
