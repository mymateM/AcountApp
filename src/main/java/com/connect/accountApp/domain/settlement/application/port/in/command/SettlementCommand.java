package com.connect.accountApp.domain.settlement.application.port.in.command;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class SettlementCommand {

  private Long giverId;
  private Long senderId;
  private BigDecimal settlementAmount;

  public SettlementCommand(Long giverId, Long senderId, BigDecimal settlementAmount) {
    this.giverId = giverId;
    this.senderId = senderId;
    this.settlementAmount = settlementAmount;
  }
}
