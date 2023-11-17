package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.settlement.application.port.in.GetHouseholdSettlementUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.HouseholdSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.RoommateSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdSettlementService implements GetHouseholdSettlementUseCase {

  private final GetUserPort getUserPort;
  private final FindHouseholdUserListPort findHouseholdUserListPort;
  private final FindSettlementPort findSettlementPort;
  private final FindExpensePort findExpensePort;


  @Override
  public HouseholdSettlementCommand getRoommateSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    BigDecimal householdTotalExpenses =
            findExpensePort.findHouseholdTotalExpenses(user.getHousehold().getHouseholdId(), startDate, endDate); // 가구에서 전체 사용한 돈
    BigDecimal userRealExpense = findSettlementPort.findUserRealExpense(user.getUserId(), startDate, endDate); // 실제 지출한 돈

    BigDecimal userRatioExpense = householdTotalExpenses.multiply(BigDecimal.valueOf(user.getUserRatio() / 100.0)); // 지출해야 하는 돈(실제 가구가 사용한 돈에서)

    // 실제 지출한 돈 - 지출 해야하는 돈 -> 실제 지출한 돈이 더 크면, 즉 양수면 받아야지 => sender : false
    BigDecimal userSettlement = userRealExpense.subtract(userRatioExpense).abs();

    UserSettlementCommand userSettlementCommand = new UserSettlementCommand(user.getUserId(), user.getUserNickname(),
            userRealExpense, userRatioExpense);

    List<User> allMembers = findHouseholdUserListPort.findHouseholdUserList(user.getHousehold());
    List<User> members = allMembers.stream().filter(member -> !Objects.equals(member.getUserId(), user.getUserId())).toList();

    int ratioSum = members.stream()
            .mapToInt(User::getUserRatio)
            .sum();

    List<RoommateSettlementCommand> roommateSettlementCommands = members.stream()
            .map(member -> new RoommateSettlementCommand(member.getUserNickname(), member.getUserId(),
                    userSettlement.multiply(BigDecimal.valueOf(member.getUserRatio() / ratioSum)),
                    member.getUserAccountBank().getBankName(), member.getUserAccount())).toList();

    return new HouseholdSettlementCommand(userSettlementCommand, roommateSettlementCommands);
  }

}
