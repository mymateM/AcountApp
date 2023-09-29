package com.connect.accountApp.settlement.application.service;

import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.settlement.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.settlement.application.port.in.command.HouseholdSettlementCommand;
import com.connect.accountApp.settlement.application.port.in.command.RoommateSettlementCommand;
import com.connect.accountApp.settlement.application.port.in.command.SettlementCommand;
import com.connect.accountApp.settlement.application.port.in.command.UserSettlementCommand;
import com.connect.accountApp.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.settlement.application.port.out.command.ExpenseOfHouseholdCommand;
import com.connect.accountApp.settlement.application.port.out.command.ExpenseOfHouseholdCommand.ExpenseRatioOfUser;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdSettlementService implements GetHouseholdSettlementUseCase {

  private final GetUserPort getUserPort;
  private final FindHouseholdUserListPort findHouseholdUserListPort;
  private final FindSettlementPort findSettlementPort;

//  private Map<Long, BigDecimal> creditorMap = new LinkedHashMap<>();
//  private Map<Long, BigDecimal> debtorMap = new LinkedHashMap<>();
//
//  private List<SettlementCommand> settlements = new ArrayList<>();



  @Override
  public HouseholdSettlementCommand getRoommateSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

    Map<Long, BigDecimal> creditorMap = new LinkedHashMap<>();
    Map<Long, BigDecimal> debtorMap = new LinkedHashMap<>();
    List<SettlementCommand> settlements = new ArrayList<>();

    User user = getUserPort.findUserWithHousehold(userEmail);
    BigDecimal userSettlement = getUserSettlement(user, startDate, endDate);

    addSettlementToMap(userSettlement, user, creditorMap, debtorMap);

    List<User> householdMembers = findHouseholdUserListPort.findHouseholdMembers(user.getHousehold().getHouseholdId());
    List<User> householdMembersWithoutUser = householdMembers.stream().filter(
        householdMember -> !user.getUserId().equals(householdMember.getUserId())).toList();

    householdMembersWithoutUser.forEach(
        householdMember -> {
          BigDecimal settlement = getUserSettlement(householdMember, startDate, endDate);
          addSettlementToMap(settlement, householdMember, creditorMap, debtorMap);
        }
    );

    List<Long> keySetDesc = sortMap(creditorMap);
    List<Long> keySetAsc = sortMap(debtorMap);

    // 1. 먼저 같은 얘들은 빼기
    findEqualSettlement(keySetDesc, keySetAsc, creditorMap, debtorMap, settlements);

    //2. 제일 큰 얘들부터 비교해가며 빼주기

    for (int i = 0; i < keySetDesc.size(); i++) {
      Long key = keySetDesc.get(i);
      BigDecimal creditorSettlement = creditorMap.get(key);



      for (int j = 0; j < keySetAsc.size(); j++) {
        Long key2 = keySetAsc.get(j);
        BigDecimal debtorSettlement = debtorMap.get(key2);
        if (creditorSettlement.compareTo(debtorSettlement) < 0) { // 채권자가 받을 돈이 채무자가 줄 돈보다 작다면
          settlements.add(new SettlementCommand(key2, key, creditorSettlement));
          debtorMap.replace(key2 ,debtorMap.get(key2).subtract(creditorSettlement));
          creditorMap.remove(key);
          keySetDesc.remove(key);
          i--;
          break;

        } else { // // 채권자가 받을 돈이 채무자가 줄 돈보다 크다면
          settlements.add(new SettlementCommand(key2, key, debtorSettlement));
          creditorMap.replace(key ,creditorMap.get(key).subtract(debtorSettlement));
          debtorMap.remove(key2);
          keySetAsc.remove(key2);
          j--;
        }
      }

    }

    List<SettlementCommand> filteredSettlements = settlements.stream()
        .filter(settlement -> {
          return settlement.getGiverId().equals(user.getUserId()) || settlement.getSenderId()
              .equals(user.getUserId());
        }).toList();


    boolean isSender;

    if (filteredSettlements.get(0).getSenderId().equals(user.getUserId())) {
      isSender = true;
    } else {
      isSender = false;
    }

    List<RoommateSettlementCommand> RoommateSettlementCommands;
    BigDecimal totalAmount = filteredSettlements.stream().map(SettlementCommand::getSettlementAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    UserSettlementCommand userSettlementCommand = new UserSettlementCommand(user.getUserId(),
        user.getUserNickname(), true, totalAmount);

    RoommateSettlementCommands = getRoommateSettlementCommands(filteredSettlements, isSender);

    return new HouseholdSettlementCommand(userSettlementCommand, RoommateSettlementCommands);
  }

  private List<RoommateSettlementCommand> getRoommateSettlementCommands(
      List<SettlementCommand> filteredSettlements, boolean isSender) {
    List<RoommateSettlementCommand> commands;
    if (isSender) {
      commands = filteredSettlements.stream().map(
          filteredSettlement -> {
            User giver = getUserPort.getUser(filteredSettlement.getGiverId());
            return new RoommateSettlementCommand(
                giver.getUserNickname(),
                giver.getUserId(),
                filteredSettlement.getSettlementAmount(),
                giver.getUserAccountBank().name(),
                giver.getUserAccount());


          }

      ).toList();

    } else {
      commands = filteredSettlements.stream().map(
          filteredSettlement -> {
            User giver = getUserPort.getUser(filteredSettlement.getSenderId());
            return new RoommateSettlementCommand(
                giver.getUserNickname(),
                giver.getUserId(),
                filteredSettlement.getSettlementAmount(),
                giver.getUserAccountBank().name(),
                giver.getUserAccount());

          }

      ).toList();
    }
    return commands;
  }


  private void payBackSettlement() {

  }

  private void findEqualSettlement(List<Long> keySetDesc, List<Long> keySetAsc,
      Map<Long, BigDecimal> creditorMap, Map<Long, BigDecimal> debtorMap,
      List<SettlementCommand> settlements)  {
    for (Long key : keySetDesc) {
      BigDecimal bigDecimal = creditorMap.get(key);
      for (Long key2 : keySetAsc) {
        if(debtorMap.get(key2).equals(bigDecimal)) { // - 주는 것, + 받는 것
          settlements.add(new SettlementCommand(key2, key, bigDecimal));
          creditorMap.remove(key);
          debtorMap.remove(key2);
          keySetDesc.remove(key);
          keySetAsc.remove(key2);
        }
      }
    }
  }

  private void addSettlementToMap(BigDecimal userSettlement, User user, Map<Long, BigDecimal> creditorMap, Map<Long, BigDecimal> debtorMap) {
    if (userSettlement.compareTo(BigDecimal.ZERO) < 0) {
      debtorMap.put(user.getUserId(), userSettlement.abs());
    } else if (userSettlement.compareTo(BigDecimal.ZERO) > 0) {
      creditorMap.put(user.getUserId(), userSettlement);
    }
  }

  private List<Long> sortMap(Map<Long, BigDecimal> map) {

    List<Long> keySetDesc = new ArrayList<>(map.keySet());

    keySetDesc.sort(new Comparator<Long>() { // 내림차순 정렬
      @Override
      public int compare(Long o1, Long o2) {
        return map.get(o2).compareTo(map.get(o1)); //todo : bigdecimal compareTo 정리
      }
    });

    return keySetDesc;
  }
  // 이 아래부터 userSettlement 와 중복임


  private BigDecimal getUserSettlement(User user, LocalDate from, LocalDate to) {
    BigDecimal userTotalRealExpense = getUserTotalRealExpense(user.getUserEmail(), from, to);
    BigDecimal userRatioExpense = getUserRatioExpense(user, from, to);
    return userTotalRealExpense.subtract(userRatioExpense); // 실제 지출 - 정산 비율 : (-) 주어야 하는 입장 : 채무자, (+) 받아야하는 입장 : 채권자
  }

  private BigDecimal getUserTotalRealExpense(String userEmail, LocalDate from, LocalDate to) {
    List<BigDecimal> userRealExpenses = findSettlementPort.findUserRealExpense(userEmail, from, to);
    return userRealExpenses.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getUserRatioExpense(User user, LocalDate from, LocalDate to) {
    List<ExpenseOfHouseholdCommand> expenseOfHouseholdCommands = findSettlementPort.findHouseholdExpenses(user.getHousehold().getHouseholdId(), from, to);
    List<ExpenseOfHouseholdCommand> filteredExpenseOfHouseholdCommands = filterExpenseOfHouseholdCommandIncludingUser(expenseOfHouseholdCommands, user);

    return getUserRatioExpense(filteredExpenseOfHouseholdCommands, user);
  }


  private List<ExpenseOfHouseholdCommand> filterExpenseOfHouseholdCommandIncludingUser(List<ExpenseOfHouseholdCommand> commands, User user) {

    return commands.stream()
        .filter(expenseOfHouseholdCommand -> expenseOfHouseholdCommand
            .getExpenseRatioOfUsers()
            .stream().anyMatch(
                expenseRatioOfUser -> expenseRatioOfUser.getUserId().equals(user.getUserId())))
        .toList();
  }

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

  private BigDecimal getUserRealRatio(ExpenseOfHouseholdCommand expenseOfHouseholdCommand, User user) {

    List<ExpenseRatioOfUser> expenseRatioOfUsers = expenseOfHouseholdCommand.getExpenseRatioOfUsers();

    int ratioSumOfUsers = expenseRatioOfUsers.stream()
        .mapToInt(ExpenseRatioOfUser::getUserExpenseRatio)
        .sum();

    return BigDecimal.valueOf(user.getUserRatio() / (double) ratioSumOfUsers);
  }
}
