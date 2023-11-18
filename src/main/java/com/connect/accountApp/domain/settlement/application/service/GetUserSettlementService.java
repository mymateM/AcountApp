package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.GetHouseholdTotalExpensePort;
import com.connect.accountApp.domain.settlement.application.port.in.GetUserSettlementUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementWithHouseholdTotalExpenseCommand;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserSettlementService implements GetUserSettlementUseCase {

  private final GetUserPort getUserPort;
  private final FindSettlementPort findSettlementPort;
  private final GetHouseholdTotalExpensePort getHouseholdTotalExpensePort;

  @Override
  public UserSettlementWithHouseholdTotalExpenseCommand getUserSettlement(String userEmail, LocalDate startDate, LocalDate endDate) {

    User user = getUserPort.findUserWithHousehold(userEmail);
    BigDecimal userRealTotalExpense = getUserTotalRealExpense(user.getUserId(), startDate, endDate);

    BigDecimal householdTotalExpense =
            getHouseholdTotalExpensePort.getHouseholdTotalExpenseByDate(user.getHousehold().getHouseholdId(), startDate.atStartOfDay(), endDate.atStartOfDay());
    BigDecimal userRatioExpense = householdTotalExpense.multiply(BigDecimal.valueOf(user.getUserRatio() / 100.0));

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
    return findSettlementPort.findUserRealExpense(userId, from, to);
  }
}
