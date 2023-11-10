package com.connect.accountApp.domain.household.application.port.in.command;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserHomeCommand {

    private Long userId;
    private BigDecimal userTotalBudget;
    private BigDecimal userByNowTotalExpense;
    private BigDecimal userByNowLeftExpense;

    public GetUserHomeCommand(Long userId, BigDecimal userTotalBudget, BigDecimal userByNowTotalExpense,
                              BigDecimal userByNowLeftExpense) {
        this.userId = userId;
        this.userTotalBudget = userTotalBudget;
        this.userByNowTotalExpense = userByNowTotalExpense;
        this.userByNowLeftExpense = userByNowLeftExpense;
    }
}
