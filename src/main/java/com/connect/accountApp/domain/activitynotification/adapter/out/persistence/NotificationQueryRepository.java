package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.QNotificationJpaEntity.notificationJpaEntity;
import static com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.QUserNotificationJpaEntity.userNotificationJpaEntity;

import com.connect.accountApp.domain.activitynotification.application.port.out.command.FindExpenseNotificationCommand;
import com.connect.accountApp.domain.activitynotification.application.port.out.command.NotificationCommand;
import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
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
            userNotificationJpaEntity.notificationJpaEntity.activityNotificationId,
            userNotificationJpaEntity.notificationJpaEntity.createdAt,
            userNotificationJpaEntity.notificationJpaEntity.activityNotificationCategory,
            userNotificationJpaEntity.notificationJpaEntity.message,
            userNotificationJpaEntity.notificationJpaEntity.isRead,
            userNotificationJpaEntity.notificationJpaEntity.title
        ))
        .from(userNotificationJpaEntity)
        .join(userNotificationJpaEntity.notificationJpaEntity, notificationJpaEntity)
        .where(
            eqUserId(userId),
            notInNotiCategory(NotiCategory.ALARM_EXPENSE) // 지출이 아닌 것
        )
        .orderBy(userNotificationJpaEntity.notificationJpaEntity.createdAt.desc())
        .fetch();
  }


  public List<FindExpenseNotificationCommand> findExpenseNotifications(Long userId) {

    return jpaQueryFactory
        .select(Projections.constructor(FindExpenseNotificationCommand.class,
            expenseJpaEntity.expenseCategory.as("category"), // 카테고리
            userNotificationJpaEntity.notificationJpaEntity.createdAt.as("createdAt"),
            userNotificationJpaEntity.notificationJpaEntity.isRead,
            expenseJpaEntity.expenseAmount,
            userNotificationJpaEntity.notificationJpaEntity.title
        ))
        .from(userNotificationJpaEntity)
        .join(userNotificationJpaEntity.notificationJpaEntity, notificationJpaEntity)
//        .join(notificationJpaEntity.expenseJpaEntity, expenseJpaEntity)
        .where(
            eqUserId(userId),
            eqNotiCategory(NotiCategory.ALARM_EXPENSE)
        )
        .orderBy(userNotificationJpaEntity.notificationJpaEntity.createdAt.desc())
        .fetch();
  }


  private BooleanExpression eqUserId(Long userId) {
    log.info("[NotificationQueryRepository] userId : {}", userId);
    return userId != null ? userNotificationJpaEntity.userJpaEntity.userId.eq(userId) : null;
  }

  private BooleanExpression notInNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userNotificationJpaEntity.notificationJpaEntity.activityNotificationCategory.notIn(notiCategory) : null;
  }

  private BooleanExpression eqNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userNotificationJpaEntity.notificationJpaEntity.activityNotificationCategory.eq(notiCategory) : null;
  }
}
