package com.connect.accountApp.domain.settlement.adapter.in.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementDate {

  @JsonProperty("date_start")
  private LocalDate dateStart;
  @JsonProperty("date_end")
  private LocalDate dateEnd;

  public SettlementDate(LocalDate dateStart, LocalDate dateEnd) {
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
  }
}
