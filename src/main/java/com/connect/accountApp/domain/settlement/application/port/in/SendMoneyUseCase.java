package com.connect.accountApp.domain.settlement.application.port.in;

public interface SendMoneyUseCase {

    String saveActivityNotification(String userEmail, Long userId);
}
