package com.connect.accountApp.domain.expense.application.port.out;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GetHouseholdTotalExpensePort {

  BigDecimal getHouseholdTotalExpenseByDate(Long householdId, LocalDateTime startTime, LocalDateTime endTime);
}
