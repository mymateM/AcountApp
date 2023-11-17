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
    this.isSettlementSender = isSender(realExpense, ratioExpense);
    this.settlementAmount = realExpense.subtract(ratioExpense).abs();
  }

  public UserSettlementCommand(Long id, String name, Boolean isSettlementSender,
      BigDecimal settlementAmount) {
    this.id = id;
    this.name = name;
    this.isSettlementSender = isSettlementSender;
    this.settlementAmount = settlementAmount;
  }

  private Boolean isSender(BigDecimal realExpense, BigDecimal ratioExpense) {
    BigDecimal userSettlement = realExpense.subtract(ratioExpense);
    if (userSettlement.compareTo(BigDecimal.ZERO) > 0) { // userSettlement가 양수임 -> 실제 지출 금액이 크니까 받아야지
      return false;
    }
    return true;
  }


}
