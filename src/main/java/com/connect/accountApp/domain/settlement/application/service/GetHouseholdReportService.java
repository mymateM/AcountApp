package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseByCategoryCommand;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.settlement.application.port.in.GetHouseholdReportUseCase;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand.ExpenseCommandByCategory;
import com.connect.accountApp.domain.settlement.application.port.out.command.HouseholdReportCommand.ReportDateCommand;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetHouseholdReportService implements GetHouseholdReportUseCase {

  private final GetUserPort getUserPort;
  private final GetTotalExpensePort getTotalExpensePort;

  @Override
  public HouseholdReportCommand getHouseholdReport(String userEmail, LocalDate reportStartDate) {
    User user = getUserPort.findUserWithHousehold(userEmail);

    LocalDate reportEndDate = reportStartDate.atStartOfDay().plusMonths(1).minusSeconds(1).toLocalDate();

    List<TotalExpenseByCategoryCommand> totalExpenseByCategoryCommands = getTotalExpensePort
        .getTotalExpenseGroupByCategory(user.getHousehold().getHouseholdId(), reportStartDate, reportEndDate);

//    totalExpenseByCategoryCommands.forEach(totalExpenseByCategoryCommand -> {
//      System.out.println("totalExpenseByCategoryCommand.getExpenseCategory() = " + totalExpenseByCategoryCommand.getExpenseCategory());
//      System.out.println("totalExpenseByCategoryCommand.getTotalExpenseAmount() = "
//          + totalExpenseByCategoryCommand.getTotalExpenseAmount());
//    });


    // 카테고리 별이 아닌, 모든 지출 내역
    BigDecimal totalExpense = totalExpenseByCategoryCommands.stream()
        .map(TotalExpenseByCategoryCommand::getTotalExpenseAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    List<ExpenseCommandByCategory> expenseCommandByCategories = totalExpenseByCategoryCommands.stream()
        .map(totalExpenseByCategoryCommand -> new ExpenseCommandByCategory(totalExpenseByCategoryCommand, totalExpense)
        ).toList();

    BigDecimal householdBudget = user.getHousehold().getHouseholdBudget();
    BigDecimal budgetRealExpenseDiff = totalExpense.subtract(householdBudget); // 전체 실제 지출 - 가구 예산 -> + : 예산 넘음, - 예산 넘지 않음

    ReportDateCommand reportDateCommand = new ReportDateCommand(reportStartDate, reportEndDate);
    return new HouseholdReportCommand(reportDateCommand, householdBudget, budgetRealExpenseDiff, totalExpense, expenseCommandByCategories);
  }

}
