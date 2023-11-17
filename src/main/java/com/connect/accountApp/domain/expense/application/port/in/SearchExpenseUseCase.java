package com.connect.accountApp.domain.expense.application.port.in;

import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SearchedCondition;
import java.util.List;

public interface SearchExpenseUseCase {

    List<DailyExpenseCommand> getSearchedExpense(String userEmail, SearchedCondition request);
}
