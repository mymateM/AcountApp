package com.connect.accountApp.domain.expense.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;

import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ExpenseQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<TotalExpenseCommand> getTotalExpenseQuery(Long householdId, LocalDate date) {

    return queryFactory
        .select(Projections.constructor(TotalExpenseCommand.class,
            userJpaEntity.userId,
            userJpaEntity.userName,
            expenseJpaEntity.expenseAmount.sum().as("userTotalExpense"),
            userJpaEntity.userRatio
        ))
        .from(expenseJpaEntity)
        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
        .where(
            eqHouseholdId(householdId),
            betweenDate(date.atStartOfDay())
        )
        .groupBy(userJpaEntity.userId)
        .fetch();
  }

  private BooleanExpression eqHouseholdId(Long householdId) {
    log.info("householdId : {}", householdId);
    return householdId != null ? userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId) : null;
  }

  private BooleanExpression betweenDate(LocalDateTime date) {
    log.info("startDate : {}, endDate : {}",date.plusDays(1).minusSeconds(1), date);
    return date != null ? expenseJpaEntity.expenseDate
        .between(date.minusMonths(1).minusSeconds(1), date) : null;
  }

}
