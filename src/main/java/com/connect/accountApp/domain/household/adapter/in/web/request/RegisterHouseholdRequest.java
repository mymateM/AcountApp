package com.connect.accountApp.domain.household.adapter.in.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterHouseholdRequest {

  @Length(max = 10, message = "글자 수가 10 글자를 초과합니다.")
  @JsonProperty("household_name")
  private String householdName;

  @Size(min = 1, max = 31, message = "일자에 해당하는 값이 아닙니다.(범위 1~31)")
  @JsonProperty("household_settlement_day_of_month")
  private Integer householdSettlementDayOfMonth;
  @JsonProperty("household_budget")
  private HouseholdBudget householdBudget;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class HouseholdBudget {

    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("allowance_ratio")
    private Integer allowanceRatio;
  }


}
