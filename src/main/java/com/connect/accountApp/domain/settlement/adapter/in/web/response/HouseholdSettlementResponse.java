package com.connect.accountApp.domain.settlement.adapter.in.web.response;

import com.connect.accountApp.domain.settlement.application.port.in.command.UserSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.HouseholdSettlementCommand;
import com.connect.accountApp.domain.settlement.application.port.in.command.RoommateSettlementCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdSettlementResponse {

  @JsonProperty("settlement_date")
  private SettlementDate settlementDate;

  @JsonProperty("user")
  private UserSettlementResponse userSettlementResponse;

  @JsonProperty("roommates")
  private List<RoommateSettlementResponse> roommateSettlementResponses;

  public HouseholdSettlementResponse(HouseholdSettlementCommand command, LocalDate startDate, LocalDate endDate) {
    this.settlementDate = new SettlementDate(startDate, endDate);
    this.userSettlementResponse = new UserSettlementResponse(command.getUserSettlementCommand());
    this.roommateSettlementResponses = command.getRoommateSettlementCommands().stream().map(RoommateSettlementResponse::new).collect(Collectors.toList());
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class UserSettlementResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_settlement_sender")
    private Boolean isSettlementSender;
    @JsonProperty("settlement_amount")
    private BigDecimal settlementAmount;

    public UserSettlementResponse(UserSettlementCommand command) {
      this.id = command.getId();
      this.name = command.getName();
//      this.isSettlementSender = command.getIsSettlementSender();
      this.isSettlementSender = false;
      this.settlementAmount = command.getSettlementAmount().setScale(0, RoundingMode.FLOOR);
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public class RoommateSettlementResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("settlement_amount")
    private BigDecimal settlementAmount;
    @JsonProperty("account_bank")
    private String accountBank;
    @JsonProperty("account_number")
    private String accountNumber;

    public RoommateSettlementResponse(RoommateSettlementCommand command) {
      this.id = command.getId();
      this.name = command.getName();
      this.settlementAmount = command.getSettlementAmount().setScale(0, RoundingMode.FLOOR);
      this.accountBank = command.getAccountBank();
      this.accountNumber = command.getAccountNumber();
    }
  }

}
