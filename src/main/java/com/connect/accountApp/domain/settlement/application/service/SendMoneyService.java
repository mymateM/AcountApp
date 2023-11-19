package com.connect.accountApp.domain.settlement.application.service;

import static com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory.REQUEST_SETTLEMENT;

import com.connect.accountApp.domain.activitynotification.application.port.out.FindActivityNotificationsPort;
import com.connect.accountApp.domain.activitynotification.application.port.out.SaveActivityNotificationPort;
import com.connect.accountApp.domain.activitynotification.domain.model.ActivityNotification;
import com.connect.accountApp.domain.settlement.application.port.in.SendMoneyUseCase;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.usernotification.application.port.out.SaveUserActivityNotificationPort;
import com.connect.accountApp.domain.usernotification.domain.model.UserActivityNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    private final GetUserPort getUserPort;
    private final SaveActivityNotificationPort saveActivityNotificationPort;
    private final FindActivityNotificationsPort findActivityNotificationsPort;
    private final SaveUserActivityNotificationPort saveUserActivityNotificationPort;

    @Override
    public String saveActivityNotification(String userEmail, Long userId) {
        User requester = getUserPort.findUserWithHousehold(userEmail);
        User receiver = getUserPort.getUser(userId);

        ActivityNotification activityNotification = createActivityNotification(requester);
        Long activityNotificationId = saveActivityNotificationPort.saveActivityNotification(activityNotification);
        ActivityNotification savedActivityNotification =
                findActivityNotificationsPort.findActivityNotification(activityNotificationId);

        UserActivityNotification userActivityNotification =
                createUserActivityNotification(receiver, savedActivityNotification);
        saveUserActivityNotificationPort.saveUserActivityNotification(userActivityNotification);

        return requester.getUserNickname();
    }

    private ActivityNotification createActivityNotification(User requester) {
        return ActivityNotification.builder()
                .title(REQUEST_SETTLEMENT.getTitle())
                .activityNotificationCategory(REQUEST_SETTLEMENT)
                .message("콕! " + requester.getUserNickname() + "님에게 이번 달 정산을 하러 가볼까요?")
                .isRead(false)
                .requester(requester)
                .build();
    }

    private UserActivityNotification createUserActivityNotification(User receiver,
                                                                    ActivityNotification activityNotification) {
        return UserActivityNotification.builder()
                .user(receiver)
                .activityNotification(activityNotification)
                .isRead(false)
                .build();
    }
}
