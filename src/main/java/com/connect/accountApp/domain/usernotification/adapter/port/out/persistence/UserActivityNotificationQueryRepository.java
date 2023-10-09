package com.connect.accountApp.domain.usernotification.adapter.port.out.persistence;

import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;
import static com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.QUserActivityNotificationJpaEntity.userActivityNotificationJpaEntity;

import com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.UserActivityNotificationJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserActivityNotificationQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<UserActivityNotificationJpaEntity> findActivityNotifications(List<Long> activityNotificationIds) {

    return jpaQueryFactory
        .selectFrom(userActivityNotificationJpaEntity)
        .join(userActivityNotificationJpaEntity.userJpaEntity, userJpaEntity)
        .fetchJoin()
        .where(
            userActivityNotificationJpaEntity.userNotiId.in(activityNotificationIds)
        )
        .fetch();
  }

}
