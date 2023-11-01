package com.connect.accountApp.domain.bill.application.port.out;

import java.util.List;

public interface DeleteBillPort {

    void deleteBills(List<Long> billIds);

}
