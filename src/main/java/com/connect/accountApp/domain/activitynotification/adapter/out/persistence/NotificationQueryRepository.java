package com.connect.accountApp.domain.activitynotification.adapter.out.persistence;

import static com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.QActivityNotificationJpaEntity.activityNotificationJpaEntity;
import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.QUserActivityNotificationJpaEntity.userActivityNotificationJpaEntity;

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
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationId,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.message,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.isRead,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.title
        ))
        .from(userActivityNotificationJpaEntity)
        .join(userActivityNotificationJpaEntity.activityNotificationJpaEntity, activityNotificationJpaEntity)
        .where(
            eqUserId(userId),
            notInNotiCategory(NotiCategory.ACCEPT_INVITATION) // 지출이 아닌 것
        )
        .orderBy(userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt.desc())
        .fetch();
  }


  public List<FindExpenseNotificationCommand> findExpenseNotifications(Long userId) {

    return jpaQueryFactory
        .select(Projections.constructor(FindExpenseNotificationCommand.class,
            expenseJpaEntity.expenseCategory.as("category"), // 카테고리
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt.as("createdAt"),
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.isRead,
            expenseJpaEntity.expenseAmount,
            userActivityNotificationJpaEntity.activityNotificationJpaEntity.title
        ))
        .from(userActivityNotificationJpaEntity)
        .join(userActivityNotificationJpaEntity.activityNotificationJpaEntity, activityNotificationJpaEntity)
//        .join(notificationJpaEntity.expenseJpaEntity, expenseJpaEntity)
        .where(
            eqUserId(userId),
            eqNotiCategory(NotiCategory.ACCEPT_INVITATION)
        )
        .orderBy(userActivityNotificationJpaEntity.activityNotificationJpaEntity.createdAt.desc())
        .fetch();
  }


  private BooleanExpression eqUserId(Long userId) {
    log.info("[NotificationQueryRepository] userId : {}", userId);
    return userId != null ? userActivityNotificationJpaEntity.userJpaEntity.userId.eq(userId) : null;
  }

  private BooleanExpression notInNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userActivityNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory.notIn(notiCategory) : null;
  }

  private BooleanExpression eqNotiCategory(NotiCategory notiCategory) {
    log.info("[NotificationQueryRepository] notiCategory : {}", notiCategory);
    return notiCategory != null ? userActivityNotificationJpaEntity.activityNotificationJpaEntity.activityNotificationCategory.eq(notiCategory) : null;
  }
}
