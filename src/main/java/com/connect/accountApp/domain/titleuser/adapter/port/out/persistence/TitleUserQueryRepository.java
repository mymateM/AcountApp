package com.connect.accountApp.domain.titleuser.adapter.port.out.persistence;

import static com.connect.accountApp.domain.titleuser.adapter.port.out.persistence.jpa.model.QTitleUserJpaEntity.titleUserJpaEntity;

import com.connect.accountApp.domain.titleuser.application.port.out.command.UserTitleCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TitleUserQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public UserTitleCommand findTitleUserByUserId(Long userId) {
    return jpaQueryFactory
        .select(Projections.constructor(UserTitleCommand.class,
            titleUserJpaEntity.createdAt,
            titleUserJpaEntity.title
        ))
        .from(titleUserJpaEntity)
        .where(titleUserJpaEntity.titleUserId.eq(userId))
        .fetchOne();
  }


}
