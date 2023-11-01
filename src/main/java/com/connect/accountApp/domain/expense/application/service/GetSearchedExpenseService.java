package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.SearchExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SearchedCondition;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSearchedExpenseService implements SearchExpenseUseCase {

    private final GetUserPort getUserPort;
    private final FindExpensePort findExpensePort;

    @Override
    public List<DailyExpenseCommand> getSearchedExpense(String userEmail, SearchedCondition condition) {

        User user = getUserPort.findUserWithHousehold(userEmail);
        Long householdId = user.getHousehold().getHouseholdId();

        List<DailyExpenseCommand> DailyExpenseCommands = findExpensePort.findSearchedExpenses(householdId, condition);

        return DailyExpenseCommands;
    }
}
