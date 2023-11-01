package com.connect.accountApp.domain.bill.application.port.in;

import java.util.List;

public interface DeleteBillsUseCase {

    void deleteBills(String userEmail, List<Long> billIds);
}
