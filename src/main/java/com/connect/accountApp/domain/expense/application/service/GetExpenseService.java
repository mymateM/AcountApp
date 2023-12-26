package com.connect.accountApp.domain.expense.application.service;

import com.connect.accountApp.domain.expense.application.port.in.GetExpenseUseCase;
import com.connect.accountApp.domain.expense.application.port.out.FindExpensePort;
import com.connect.accountApp.domain.expense.domain.model.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExpenseService implements GetExpenseUseCase {

    private final FindExpensePort findExpensePort;

    @Override
    public Expense getExpense(Long expenseId) {
        return findExpensePort.findExpense(expenseId);
    }
}
