package com.connect.accountApp.settlement.application.port.out;

import com.connect.accountApp.settlement.application.port.out.command.ExpenseOfHouseholdCommand;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FindSettlementPort {

  List<BigDecimal> findUserRealExpense(String userEmail, LocalDate startDate, LocalDate endDate);

  List<ExpenseOfHouseholdCommand> findHouseholdExpenses(Long householdId, LocalDate startDate, LocalDate endDate);

}
