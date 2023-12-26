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

  public UserJpaEntity findUserJpaEntityWithHouseholdByEmail(String userEmail) {

    return jpaQueryFactory
        .select(userJpaEntity)
        .from(userJpaEntity)
        .join(userJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
        .fetchJoin()
        .where(
            eqUserEmail(userEmail)
        )
        .fetchOne();

  }

  private BooleanExpression eqHouseholdId(Long householdId) {
    log.info("UserQueryRepository => householdId : {}", householdId);
    return householdId != null ? userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId) : null;
  }

  private BooleanExpression eqUserEmail(String userEmail) {
    log.info("UserQueryRepository => userEmail : {}", userEmail);
    return userEmail != null ? userJpaEntity.userEmail.eq(userEmail) : null;
  }

}
