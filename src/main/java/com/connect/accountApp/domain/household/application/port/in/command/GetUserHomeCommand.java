package com.connect.accountApp.domain.household.application.port.in.command;

import com.connect.accountApp.global.utils.NvlUtils;
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

    public GetUserHomeCommand(Long userId, BigDecimal userTotalBudget, BigDecimal userByNowTotalExpense) {
        BigDecimal nvlUserTotalBudget = NvlUtils.nvl(userTotalBudget);
        BigDecimal nvlUserByNowTotalExpense = NvlUtils.nvl(userByNowTotalExpense);
        this.userId = userId;
        this.userTotalBudget = nvlUserTotalBudget;
        this.userByNowTotalExpense = nvlUserByNowTotalExpense;
        this.userByNowLeftExpense = nvlUserTotalBudget.subtract(nvlUserByNowTotalExpense);
    }
}
