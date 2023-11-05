package com.connect.accountApp.domain.bill.application.service;

import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.bill.application.port.in.DeleteBillsUseCase;
import com.connect.accountApp.domain.bill.application.port.out.DeleteBillPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBillsService implements DeleteBillsUseCase {

    private final DeleteBillPort deleteBillPort;
    private final FindActivityNotificationsPort findActivityNotificationsPort;
    private final SaveActivityNotificationPort saveActivityNotificationPort;

    @Override
    public void deleteBills(String userEmail, List<Long> billIds) {
        List<ActivityNotification> userActivityNotificationByBill = findActivityNotificationsPort.findUserActivityNotificationByBill(
                billIds);

        userActivityNotificationByBill.stream().forEach(ActivityNotification::setBillNull);
        saveActivityNotificationPort.saveAllActivityNotification(userActivityNotificationByBill);

        deleteBillPort.deleteBills(billIds);
    }
}
