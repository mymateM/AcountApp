package com.connect.accountApp.domain.user.adapter.in.web.response;

import com.connect.accountApp.domain.user.application.port.in.command.GetMyPageCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMyPageResponse {

    @JsonProperty("user_image_url")
    private String userImageUrl;
    @JsonProperty("user_nickname")
    private String userNickname;
    @JsonProperty("user_settlement_ratio")
    private Integer userSettlementRatio;
    @JsonProperty("household_settlement_date")
    private LocalDate householdSettlementDate;
    @JsonProperty("household_budget_amount")
    private BigDecimal householdBudgetAmount;

    public GetMyPageResponse(GetMyPageCommand command) {
        this.userImageUrl = command.getUserImageUrl();
        this.userNickname = command.getUserNickname();
        this.userSettlementRatio = command.getUserSettlementRatio();
        this.householdSettlementDate = command.getHouseholdSettlementDate();
        this.householdBudgetAmount = command.getHouseholdBudgetAmount().setScale(0, RoundingMode.FLOOR);
    }
}
