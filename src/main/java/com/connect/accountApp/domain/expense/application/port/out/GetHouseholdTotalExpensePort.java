package com.connect.accountApp.domain.expense.application.port.out;

import java.time.LocalDateTime;

public interface GetHouseholdTotalExpensePort {

  int getHouseholdTotalExpense(Long householdId, LocalDateTime startTime, LocalDateTime endTime);

}
