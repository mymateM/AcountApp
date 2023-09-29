package com.connect.accountApp.domain.settlement.application.port.in.command;

import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserSettlementCommand {

  private Long id;
  private String name;
  private BigDecimal realExpense;
  private BigDecimal ratioExpense;
  private Boolean isSettlementSender;
  private BigDecimal settlementAmount;

  public UserSettlementCommand(Long id, String name, BigDecimal realExpense, BigDecimal ratioExpense) {
    this.id = id;
    this.name = name;
    this.realExpense = realExpense;
    this.ratioExpense = ratioExpense;
    this.isSettlementSender = realExpense.compareTo(ratioExpense) < 0; // realExpense < ratioExpense : 보내는 사람
    this.settlementAmount = realExpense.subtract(ratioExpense).abs();
  }

  public UserSettlementCommand(Long id, String name, Boolean isSettlementSender,
      BigDecimal settlementAmount) {
    this.id = id;
    this.name = name;
    this.isSettlementSender = isSettlementSender;
    this.settlementAmount = settlementAmount;
  }
}
