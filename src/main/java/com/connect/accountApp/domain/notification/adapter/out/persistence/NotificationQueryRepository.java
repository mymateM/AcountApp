package com.connect.accountApp.domain.notification.adapter.out.persistence;

import static com.connect.accountApp.domain.notification.adapter.out.persistence.jpa.model.QNotificationJpaEntity.notificationJpaEntity;
import static com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.QUserNotificationJpaEntity.userNotificationJpaEntity;

import com.connect.accountApp.domain.notification.adapter.out.persistence.jpa.model.NotificationJpaEntity;
import com.connect.accountApp.domain.notification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.notification.domain.model.NotiCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

  public List<NotificationCommand> findActivityNotifications(Long userId) {

    return jpaQueryFactory
        .select(Projections.constructor(NotificationCommand.class,
            userNotificationJpaEntity.notificationJpaEntity.notiId,
            userNotificationJpaEntity.notificationJpaEntity.notiCreatedAt,
            userNotificationJpaEntity.notificationJpaEntity.notiCategory,
            userNotificationJpaEntity.notificationJpaEntity.notiContent,
            userNotificationJpaEntity.notificationJpaEntity.notiIsRead,
            userNotificationJpaEntity.notificationJpaEntity.senderName
        ))
        .from(userNotificationJpaEntity)
        .join(userNotificationJpaEntity.notificationJpaEntity, notificationJpaEntity)
        .where(
            eqUserId(userId),
            notInNotiCategory(NotiCategory.ALARM_EXPENSE) // 지출이 아닌 것
        )
        .fetch();
  }

  private BooleanExpression eqUserId(Long userId) {
    log.info("[NotificationQueryRepository] userId : {}", userId);
    return userId != null ? userNotificationJpaEntity.userJpaEntity.userId.eq(userId) : null;
  }

  private BooleanExpression notInNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userNotificationJpaEntity.notificationJpaEntity.notiCategory.notIn(notiCategory) : null;
  }
}
