package com.connect.accountApp.domain.expense.application.port.out;

import java.time.LocalDate;

public interface GetHouseholdTotalExpensePort {

  int getHouseholdTotalExpense(Long householdId, LocalDate date);

}
