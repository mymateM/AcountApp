package com.connect.accountApp.settlement.adapter.in.web.response;

import com.connect.accountApp.settlement.application.port.in.command.UserSettlementCommand;
import com.connect.accountApp.settlement.application.port.in.command.UserSettlementCommand.UserCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSettlementResponse {

  @JsonProperty("household_expense_total")
  private BigDecimal householdExpenseTotal;

  @JsonProperty("settlement_date")
  private SettlementDate settlementDate;

  @JsonProperty("user")
  private User user;

  public UserSettlementResponse(BigDecimal householdExpenseTotal,
      SettlementDate settlementDate,
      User user) {
    this.householdExpenseTotal = householdExpenseTotal;
    this.settlementDate = settlementDate;
    this.user = user;
  }

  public UserSettlementResponse(UserSettlementCommand command, LocalDate startDate, LocalDate endDate) {
    this.householdExpenseTotal = command.getHouseholdExpenseTotal();
    this.settlementDate = new SettlementDate(startDate, endDate);
    this.user = new User(command.getUserCommand());
  }

  class User {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("real_expense")
    private BigDecimal realExpense;
    @JsonProperty("ratio_expense")
    private BigDecimal ratioExpense;
    @JsonProperty("is_settlement_sender")
    private Boolean isSettlementSender;
    @JsonProperty("settlement_amount")
    private BigDecimal settlementAmount;

    public User(UserCommand command) {
      this.id = command.getId();
      this.name = command.getName();
      this.realExpense = command.getRealExpense();
      this.ratioExpense = command.getRatioExpense();
      this.isSettlementSender = command.getIsSettlementSender();
      this.settlementAmount = command.getSettlementAmount();
    }
  }

}
