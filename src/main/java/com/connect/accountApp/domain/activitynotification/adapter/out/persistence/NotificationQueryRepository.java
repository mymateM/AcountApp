package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import static com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.QActivityNotificationJpaEntity.activityNotificationJpaEntity;
import static com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.QUserActivityNotificationJpaEntity.userActivityNotificationJpaEntity;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import com.connect.accountApp.domain.activitynotification.application.port.in.command.ActivityNotificationCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;


  public List<ActivityNotificationCommand> findActivityNotifications(String userEmail) {

    return jpaQueryFactory
        .select(Projections.constructor(ActivityNotificationCommand.class,
            userActivityNotificationJpaEntity.userNotiId.as("userActivityNotificationId"),
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory.as("notiCategory"),
            userActivityNotificationJpaEntity.isRead,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.requesterJpaEntity.userNickname.as("trigger")
        ))
        .from(userActivityNotificationJpaEntity)
        .join(userActivityNotificationJpaEntity.activityNotificationJpaEntity)
        .join(userActivityNotificationJpaEntity.userJpaEntity)
        .leftJoin(
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.requesterJpaEntity)
        .where(
            userActivityNotificationJpaEntity.userJpaEntity.userEmail.eq(userEmail)
        ).orderBy(userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt.desc())
        .fetch();
  }

    public List<ActivityNotificationJpaEntity> findActivityNotificationByBill(List<Long> billIds) {

        return jpaQueryFactory
                .select(activityNotificationJpaEntity)
                .from(userActivityNotificationJpaEntity)
                .join(userActivityNotificationJpaEntity.activityNotificationJpaEntity)
                .join(userActivityNotificationJpaEntity.userJpaEntity)
                .leftJoin(userActivityNotificationJpaEntity.activityNotificationJpaEntity.billJpaEntity)
                .where(
                        userActivityNotificationJpaEntity.activityNotificationJpaEntity.billJpaEntity.billId.in(billIds)
                )
                .fetch();
    }

}
