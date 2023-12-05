package com.connect.accountApp.domain.user.application.port.in.command;

import com.connect.accountApp.domain.user.domain.model.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMyPageCommand {

    private String userImageUrl;
    private String userNickname;
    private Integer userSettlementRatio;
    private Integer householdSettlementDate;
    private BigDecimal householdBudgetAmount;

    public GetMyPageCommand(User user) {

        this.userImageUrl = user.getUserImgUrl();
        this.userNickname = user.getUserNickname();
        this.userSettlementRatio = user.getUserRatio();
        this.householdSettlementDate = user.getHousehold().getHouseholdSettlementDayOfMonth();
        this.householdBudgetAmount = user.getHousehold().getHouseholdBudget();
    }
}
