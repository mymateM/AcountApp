package com.connect.accountApp.domain.user.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserSettlementRequest {

    @JsonProperty("household_members")
    List<UserSettlementRequest> householdMembers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserSettlementRequest {

        @JsonProperty("user_id")
        private Long userId;
        @JsonProperty("user_settlement_ratio")
        private Integer userSettlementRatio;
    }
}
