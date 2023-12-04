package com.connect.accountApp.domain.expensenotification.application.port.out;

import java.util.List;

public interface DeleteExpenseNotificationPort {

    void deleteExpenseNotification(List<Long> expenseIds);
}
