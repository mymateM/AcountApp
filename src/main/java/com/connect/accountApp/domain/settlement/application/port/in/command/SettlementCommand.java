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

    @Override
    public String toString() {
        return "SettlementCommand{" +
                "giverId=" + giverId +
                ", senderId=" + senderId +
                ", settlementAmount=" + settlementAmount +
                '}';
    }

//    // 내가 sender면 command에서 sender인 애들을 가져와
//    // 그 다음 sender가 같으면 filetering 된다.
//    public Boolean isSettlement(Long userId) {
//        if (this.giverId == userId) {
//
//        }
//    }
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