package com.connect.accountApp.domain.user.adapter.out.persistence.jpa;

import static com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.QHouseHoldJpaEntity.houseHoldJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;

import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<RoommateSendMoneyCommand> getRoommateSendMoney(Long householdId, Long userId) {

    return jpaQueryFactory
        .select(Projections.constructor(RoommateSendMoneyCommand.class,
            userJpaEntity.userId,
            userJpaEntity.userNickname.as("userName"),
            userJpaEntity.userRatio,
            userJpaEntity.userAccountBank,
            userJpaEntity.userAccount
        ))
        .from(userJpaEntity)
        .where(
            eqHouseholdId(householdId),
            notInUserId(userId)
        )
        .fetch();

  }

  public List<UserJpaEntity> getHouseholdMember(Long householdId) {

    return jpaQueryFactory
        .select(userJpaEntity)
        .from(userJpaEntity)
        .join(userJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
        .fetchJoin()
        .where(
            eqHouseholdId(householdId)
        )
        .fetch();

  }

  private BooleanExpression eqHouseholdId(Long householdId) {
    log.info("getRoommateSendMoney => householdId : {}", householdId);
    return householdId != null ? userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId) : null;
  }

  private BooleanExpression notInUserId(Long userId) {
    log.info("getRoommateSendMoney => userId : {}", userId);
    return userId != null ? userJpaEntity.userId.notIn(userId) : null;
  }

}
