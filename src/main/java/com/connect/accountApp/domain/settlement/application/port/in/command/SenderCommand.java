package com.connect.accountApp.domain.settlement.application.port.in.command;

import java.math.BigDecimal;

public class SenderCommand {
    private Long senderId;
    private BigDecimal SenderAmount;

    public SenderCommand(Long senderId, BigDecimal senderAmount) {
        this.senderId = senderId;
        SenderAmount = senderAmount;
    }

    public BigDecimal getSenderAmount() {
        return SenderAmount;
    }

    public void setSenderAmount(BigDecimal senderAmount) {
        SenderAmount = senderAmount;
    }

    public Long getSenderId() {
        return senderId;
    }
}
