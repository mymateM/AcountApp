package com.connect.accountApp.domain.household.adapter.in.web.response;

import com.connect.accountApp.domain.household.application.port.in.command.GetHouseholdHomeCommand;
import com.connect.accountApp.domain.household.application.port.in.command.GetUserHomeCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetHouseholdHomeResponse {

    @JsonProperty("household")
    private HouseholdResponse householdResponse;
    @JsonProperty("me")
    private MeResponse meResponse;

    public GetHouseholdHomeResponse(GetHouseholdHomeCommand householdHomeCommand, GetUserHomeCommand getUserHomeCommand) {
        this.householdResponse = new HouseholdResponse(householdHomeCommand);
        this.meResponse = meResponse;
    }

    @Getter
    @NoArgsConstructor
    public class HouseholdResponse {
        @JsonProperty("house_id")
        private Long householdId;
        @JsonProperty("household_name")
        private String householdName;
        @JsonProperty("by_now_expense")
        private BigDecimal byNowExpense;
        @JsonProperty("by_now_budget_ratio")
        private BigDecimal byNowBudgetRatio;
        @JsonProperty("settlement_d_day")
        private Integer settlementDDay;
        @JsonProperty("by_previous_expense")
        private BigDecimal byPreviousExpense;
        @JsonProperty("now_expense_diff")
        private BigDecimal nowExpenseDiff;
        @JsonProperty("is_household_budget_over_warn")
        private Boolean isHouseholdBudgetOverWarn;

        public HouseholdResponse(GetHouseholdHomeCommand command) {
            this.householdId = command.getHouseholdId();
            this.householdName = command.getHouseholdName();
            this.byNowExpense = command.getByNowExpense().setScale(0, RoundingMode.FLOOR);
            this.byNowBudgetRatio = command.getByNowBudgetRatio().setScale(0, RoundingMode.HALF_UP);
            this.settlementDDay = command.getSettlementDDay();
            this.byPreviousExpense = command.getByPreviousExpense().setScale(0, RoundingMode.FLOOR);
            this.nowExpenseDiff = command.getNowExpenseDiff().setScale(0, RoundingMode.FLOOR);
            this.isHouseholdBudgetOverWarn = command.getIsHouseholdBudgetOverWarn();
        }
    }

    @Getter
    @NoArgsConstructor
    public class MeResponse {
        @JsonProperty("user_id")
        private Long userId;
        @JsonProperty("user_total_budget")
        private BigDecimal userTotalBudget;
        @JsonProperty("user_by_now_total_expense")
        private BigDecimal userByNowTotalExpense;
        @JsonProperty("user_by_now_left_expense")
        private BigDecimal userByNowLeftExpense;

        public MeResponse(GetUserHomeCommand command) {
            this.userId = userId;
            this.userTotalBudget = userTotalBudget;
            this.userByNowTotalExpense = userByNowTotalExpense;
            this.userByNowLeftExpense = userByNowLeftExpense;
        }
    }

}
