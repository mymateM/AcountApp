package com.connect.accountApp.domain.settlement.application.service;

import com.connect.accountApp.domain.expense.application.port.out.GetTotalExpensePort;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseByCategoryCommand;
import com.connect.accountApp.domain.settlement.application.port.in.GetUserReportUseCase;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand.ExpenseCommandByCategory;
import com.connect.accountApp.domain.settlement.application.port.in.command.UserReportCommand.ReportDateCommand;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserReportService implements GetUserReportUseCase {

    private final GetUserPort getUserPort;
    private final GetTotalExpensePort getTotalExpensePort;

    @Override
    public UserReportCommand getUserReport(String userEmail, LocalDate reportStartDate) {

        User user = getUserPort.findUserWithHousehold(userEmail);

        LocalDate reportEndDate = reportStartDate.atStartOfDay().plusMonths(1).minusSeconds(1).toLocalDate();

        List<TotalExpenseByCategoryCommand> totalExpenseByCategoryCommands = getTotalExpensePort
                .getTotalUserExpenseGroupByCategory(user.getUserId(), reportStartDate, reportEndDate);
        BigDecimal totalExpense = totalExpenseByCategoryCommands.stream()
                .map(TotalExpenseByCategoryCommand::getTotalExpenseAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<ExpenseCommandByCategory> expenseCommandByCategories = totalExpenseByCategoryCommands.stream()
                .map(totalExpenseByCategoryCommand -> new ExpenseCommandByCategory(totalExpenseByCategoryCommand, totalExpense)
                ).toList();

        ReportDateCommand reportDateCommand = new ReportDateCommand(reportStartDate, reportEndDate);

        return new UserReportCommand(reportDateCommand, expenseCommandByCategories);
    }
}
