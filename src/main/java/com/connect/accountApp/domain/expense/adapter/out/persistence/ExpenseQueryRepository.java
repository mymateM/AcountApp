package com.connect.accountApp.domain.expense.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.QHouseHoldJpaEntity.houseHoldJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;

import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
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

  public List<TotalExpenseCommand> getTotalExpenseQuery(Long householdId, LocalDateTime startTime,
      LocalDateTime endTime) {

    return queryFactory
        .select(Projections.constructor(TotalExpenseCommand.class,
            userJpaEntity.userId,
            userJpaEntity.userNickname.as("userName"),
            expenseJpaEntity.expenseAmount.sum().as("userTotalExpense"),
            userJpaEntity.userRatio
        ))
        .from(expenseJpaEntity)
        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
        .where(
            eqHouseholdId(householdId),
            betweenDate(startTime, endTime)
        )
        .groupBy(userJpaEntity.userId)
        .fetch();
  }

  public TotalExpenseCommand getUserTotalExpenseQuery(Long userId, LocalDate date) {

    return queryFactory
        .select(Projections.constructor(TotalExpenseCommand.class,
            userJpaEntity.userId,
            userJpaEntity.userNickname.as("userName"),
            expenseJpaEntity.expenseAmount.sum().as("userTotalExpense"),
            userJpaEntity.userRatio
        ))
        .from(expenseJpaEntity)
        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
        .where(
            userJpaEntity.userId.eq(userId),
            betweenDate(date.atStartOfDay())
        )
        .fetchOne();
  }


  public Integer getHouseholdTotalExpense(Long householdId, LocalDateTime startTime, LocalDateTime endTime) {

//    Integer householdTotalExpense = queryFactory
//        .select(
//            expenseJpaEntity.expenseAmount.sum().as("householdTotalExpense")
//        )
//        .from(expenseJpaEntity)
//        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
//        .where(
//            eqHouseholdId(householdId),
//            betweenDate(startTime, endTime.plusDays(1).minusSeconds(1))
//        )
//        .groupBy(userJpaEntity.houseHoldJpaEntity.householdId)
//        .fetchOne();
    return 1;
  }

  public List<DailyTotalExpensesCommand> getDailyTotalExpenseOfHousehold(Long householdId, LocalDate date) {

    LocalDateTime startOfMonth = date.atStartOfDay();
    LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1L);

    return queryFactory
        .select(
            Projections.constructor(DailyTotalExpensesCommand.class,
                expenseJpaEntity.expenseDate.dayOfMonth().as("expenseDayOfMonth"),
                expenseJpaEntity.expenseAmount.sum().as("dailyTotalExpense")
                )

        )
        .from(expenseJpaEntity)
        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
        .join(userJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
        .where(
            eqHouseholdId(householdId),
            betweenDate(startOfMonth, endOfMonth)
        )
        .groupBy(expenseJpaEntity.expenseDate.dayOfMonth())
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

  private BooleanExpression betweenDate(LocalDateTime startDate, LocalDateTime endDate) {
    log.info("startDate : {}, endDate : {}",startDate, endDate);

    return (startDate != null) && (endDate != null) ? expenseJpaEntity.expenseDate
        .between(startDate, endDate) : null;
  }

}
