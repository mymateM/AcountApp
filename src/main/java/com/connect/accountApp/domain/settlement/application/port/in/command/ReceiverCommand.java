package com.connect.accountApp.domain.settlement.application.port.in.command;

import java.math.BigDecimal;

public class ReceiverCommand {
    private Long receiverId;
    private BigDecimal receiverAmount;

    public ReceiverCommand(Long receiverId, BigDecimal receiverAmount) {
        this.receiverId = receiverId;
        this.receiverAmount = receiverAmount;
    }

    public BigDecimal getReceiverAmount() {
        return receiverAmount;
    }

    public void setReceiverAmount(BigDecimal receiverAmount) {
        this.receiverAmount = receiverAmount;
    }

    public Long getReceiverId() {
        return receiverId;
    }
}
