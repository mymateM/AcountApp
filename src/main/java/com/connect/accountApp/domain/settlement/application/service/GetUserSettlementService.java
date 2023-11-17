package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.settlement.application.port.in.GetUserSettlementUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementWithHouseholdTotalExpenseCommand;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
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
  private final FindExpensePort expensePort;

  @Override
  public UserSettlementWithHouseholdTotalExpenseCommand getUserSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    BigDecimal userRealTotalExpense = getUserTotalRealExpense(user.getUserId(), startDate, endDate);

    BigDecimal householdTotalExpense =
            expensePort.findHouseholdTotalExpenses(user.getHousehold().getHouseholdId(), startDate, endDate);
    BigDecimal userRatioExpense = BigDecimal.valueOf(user.getUserRatio()).divide(householdTotalExpense)
            .multiply(BigDecimal.valueOf(100L));

    return new UserSettlementWithHouseholdTotalExpenseCommand(householdTotalExpense, userRealTotalExpense, userRatioExpense, user);
  }

  /**
   * 사용자의 실제 지출의 총 합을 반환하는 함수
   * @param userId user의 아이디 값
   * @param from 지출 소비 시작 날짜
   * @param to 지출 소비 마지막 날짜
   * @return user의 from에서 to까지 실제 지출의 총 합을 반환
   */
  private BigDecimal getUserTotalRealExpense(Long userId, LocalDate from, LocalDate to) {
    List<BigDecimal> userRealExpenses = findSettlementPort.findUserRealExpense(userId, from, to);
    return userRealExpenses.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
