package com.connect.accountApp.domain.household.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSettlementDayOfMonthRequest {

  @Min(value = 1, message = "요일은 1일 부터 28일까지 받습니다.")
  @Max(value = 28, message = "요일은 1일 부터 28일까지 받습니다.")
  @JsonProperty("settlement_day_of_month")
  private int dayOfMonth;

}
