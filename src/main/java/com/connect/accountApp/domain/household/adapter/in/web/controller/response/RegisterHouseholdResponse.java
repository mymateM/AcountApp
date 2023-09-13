package com.connect.accountApp.domain.household.adapter.in.web.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterHouseholdResponse {

  private String inviteCode;

}
