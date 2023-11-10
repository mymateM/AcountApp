package com.connect.accountApp.domain.expense.application.port.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GetHouseholdTotalExpensePort {

  int getHouseholdTotalExpense(Long householdId, LocalDateTime startTime, LocalDateTime endTime);

  BigDecimal getHouseholdTotalExpenseByDate(Long householdId, LocalDateTime startTime, LocalDateTime endTime);

  BigDecimal getUserTotalExpenseByDate(Long userId, LocalDateTime startTime, LocalDateTime endTime);

}
