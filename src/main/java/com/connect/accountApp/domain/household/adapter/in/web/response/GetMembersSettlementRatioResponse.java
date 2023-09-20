package com.connect.accountApp.domain.household.adapter.in.web.response;

import com.connect.accountApp.domain.household.application.port.in.command.GetMembersSettlementCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMembersSettlementRatioResponse {

  @JsonProperty("household_members")
  private List<HouseholdMembers> householdMembers;


  public GetMembersSettlementRatioResponse(List<GetMembersSettlementCommand> commands) {
    this.householdMembers = commands.stream().map(HouseholdMembers::new).toList();
  }

  @Getter
  @NoArgsConstructor
  class HouseholdMembers {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_profile_image")
    private String userProfileImage;

    @JsonProperty("user_nickname")
    private String userNickName;

    @JsonProperty("user_settlement_ratio")
    private Integer userSettlementRatio;

    public HouseholdMembers(GetMembersSettlementCommand command) {
      this.userId = command.getUserId();
      this.userProfileImage = command.getUserProfileImage();
      this.userNickName = command.getUserNickName();
      this.userSettlementRatio = command.getUserSettlementRatio();
    }
  }


}
