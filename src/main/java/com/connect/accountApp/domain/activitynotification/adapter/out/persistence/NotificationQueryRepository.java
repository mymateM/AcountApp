package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import static com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.QActivityNotificationJpaEntity.activityNotificationJpaEntity;
import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
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
            userNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationId,
            userNotificationJpaEntity.activityNotificationJpaEntity.createdAt,
            userNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory,
            userNotificationJpaEntity.activityNotificationJpaEntity.message,
            userNotificationJpaEntity.activityNotificationJpaEntity.isRead,
            userNotificationJpaEntity.activityNotificationJpaEntity.title
        ))
        .from(userNotificationJpaEntity)
        .join(userNotificationJpaEntity.activityNotificationJpaEntity, activityNotificationJpaEntity)
        .where(
            eqUserId(userId),
            notInNotiCategory(NotiCategory.ALARM_EXPENSE) // 지출이 아닌 것
        )
        .orderBy(userNotificationJpaEntity.activityNotificationJpaEntity.createdAt.desc())
        .fetch();
  }


  public List<FindExpenseNotificationCommand> findExpenseNotifications(Long userId) {

    return jpaQueryFactory
        .select(Projections.constructor(FindExpenseNotificationCommand.class,
            expenseJpaEntity.expenseCategory.as("category"), // 카테고리
            userNotificationJpaEntity.activityNotificationJpaEntity.createdAt.as("createdAt"),
            userNotificationJpaEntity.activityNotificationJpaEntity.isRead,
            expenseJpaEntity.expenseAmount,
            userNotificationJpaEntity.activityNotificationJpaEntity.title
        ))
        .from(userNotificationJpaEntity)
        .join(userNotificationJpaEntity.activityNotificationJpaEntity, activityNotificationJpaEntity)
//        .join(notificationJpaEntity.expenseJpaEntity, expenseJpaEntity)
        .where(
            eqUserId(userId),
            eqNotiCategory(NotiCategory.ALARM_EXPENSE)
        )
        .orderBy(userNotificationJpaEntity.activityNotificationJpaEntity.createdAt.desc())
        .fetch();
  }


  private BooleanExpression eqUserId(Long userId) {
    log.info("[NotificationQueryRepository] userId : {}", userId);
    return userId != null ? userNotificationJpaEntity.userJpaEntity.userId.eq(userId) : null;
  }

  private BooleanExpression notInNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory.notIn(notiCategory) : null;
  }

  private BooleanExpression eqNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory.eq(notiCategory) : null;
  }
}
