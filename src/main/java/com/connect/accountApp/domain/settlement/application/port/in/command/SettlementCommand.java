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

// 지출 등록
// 달별 지출 조회
// 일별 지출 조회
// 지출 1건 조회
// 지출 검색

// 정산
// 사용자 정산
// 멤버별 정산
// 홈 정산