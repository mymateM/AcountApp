package com.connect.accountApp.settlement.application.port.in.command;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoommateSettlementCommand {

  private String name;
  private Long id;
  private BigDecimal settlementAmount;
  private String accountBank;
  private String accountNumber;


  public RoommateSettlementCommand(String name, Long id, BigDecimal settlementAmount,
      String accountBank, String accountNumber) {
    this.name = name;
    this.id = id;
    this.settlementAmount = settlementAmount;
    this.accountBank = accountBank;
    this.accountNumber = accountNumber;
  }
}
