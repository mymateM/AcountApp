package com.connect.accountApp.domain.expense.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.QHouseHoldJpaEntity.houseHoldJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;

import com.connect.accountApp.domain.expense.application.port.in.command.DailyExpenseCommand;
import com.connect.accountApp.domain.expense.application.port.in.command.SearchedCondition;
import com.connect.accountApp.domain.expense.application.port.out.command.DailyTotalExpensesCommand;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseByCategoryCommand;
import com.connect.accountApp.domain.expense.application.port.out.command.TotalExpenseCommand;
import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
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

    public List<TotalExpenseCommand> getTotalExpenseQuery(Long householdId, LocalDate startTime,
                                                          LocalDate endTime) {

        return queryFactory
                .select(Projections.constructor(TotalExpenseCommand.class,
                        userJpaEntity.userId,
                        userJpaEntity.userNickname.as("userName"),
                        expenseJpaEntity.expenseAmount.sum().as("userTotalExpense"),
                        userJpaEntity.userRatio
                ))
                .from(expenseJpaEntity)
//        .join(expenseJpaEntity., userJpaEntity)
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
//        .join(expenseJpaEntity.userJpaEntity, userJpaEntity)
                .where(
                        userJpaEntity.userId.eq(userId),
                        betweenDate(date.atStartOfDay())
                )
                .fetchOne();
    }

    public List<DailyExpenseCommand> findDailyExpenses(Long householdId, LocalDate date) {

        return queryFactory
                .select(expenseJpaEntity)
                .where(
                        expenseJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        expenseJpaEntity.expenseDate.eq(date)
                )
                .transform(GroupBy.groupBy(expenseJpaEntity.expenseId).list(
                                Projections.constructor(DailyExpenseCommand.class,
                                        expenseJpaEntity.expenseId,
                                        expenseJpaEntity.expenseAmount,
                                        expenseJpaEntity.expenseStore,
                                        expenseJpaEntity.expenseCategory
                                )
                        )
                );
    }

    public List<DailyExpenseCommand> findSearchedExpenses(Long householdId, SearchedCondition condition) {

        OrderSpecifier<LocalDate> sorted = sorted(condition);

        return queryFactory
                .from(expenseJpaEntity)
                .join(expenseJpaEntity.spender, userJpaEntity)
                .where(
                        userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        expenseJpaEntity.expenseDate.between(condition.getExpenseDateMin(),
                                condition.getExpenseDateMax()),
                        eqCategory(condition.getExpenseCategory().orElse(null)),
                        expenseJpaEntity.expenseAmount.between(condition.getExpenseAmountMin(),
                                condition.getExpenseAmountMax())

                )
                .orderBy(sorted)
                .transform(GroupBy.groupBy(expenseJpaEntity.expenseId).list(
                                Projections.constructor(DailyExpenseCommand.class,
                                        expenseJpaEntity.expenseId,
                                        expenseJpaEntity.expenseAmount,
                                        expenseJpaEntity.expenseStore,
                                        expenseJpaEntity.expenseCategory
                                )
                        )
                );
    }

    private OrderSpecifier<LocalDate> sorted(SearchedCondition condition) {
//        OrderSpecifier<LocalDate> sorted = settlementJpaEntity.expenseJpaEntity.expenseDate.asc();
//        if (condition.getSortedByNewest()) {
//            sorted = settlementJpaEntity.expenseJpaEntity.expenseDate.desc();
//        }
//        return sorted;
        return null;
    }


    public BigDecimal getHouseholdTotalExpenseBetweenDate(Long householdId, LocalDateTime startTime, LocalDateTime endTime) {

        return queryFactory
                .select(
                        expenseJpaEntity.expenseAmount.sum().as("householdTotalExpense")
                )
                .from(expenseJpaEntity)
                .join(expenseJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
                .where(
                        expenseJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        betweenDate(startTime.toLocalDate(), endTime.toLocalDate())
                )
                .groupBy(expenseJpaEntity.houseHoldJpaEntity.householdId)
                .fetchOne();
    }

    public BigDecimal getUserTotalExpense(Long userId, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
//        return queryFactory
//                .select(
//                        expenseJpaEntity.expenseAmount.sum().as("userTotalExpense")
//                )
//                .from(expenseJpaEntity)
//                .join(expenseJpaEntity.spender, userJpaEntity)
//                .where(
//
//                        settlementJpaEntity.userJpaEntity.userId.eq(userId),
//                        betweenDate(startTime, endTime.plusDays(1).minusSeconds(1))
//                )
//                .groupBy(settlementJpaEntity.userJpaEntity.houseHoldJpaEntity.householdId)
//                .fetchOne();
    }

    public BigDecimal getHouseholdTotalExpense(Long householdId, LocalDate startTime, LocalDate endTime) {

        return queryFactory
                .select(expenseJpaEntity.expenseAmount.sum().as("householdTotalExpense"))
                .from(expenseJpaEntity)
                .join(expenseJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
                .where(
                        expenseJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        betweenDate(startTime, endTime) //todo: 여기 포함?
                )
                .groupBy(expenseJpaEntity.houseHoldJpaEntity.householdId)
                .fetchOne();
    }

    public List<DailyTotalExpensesCommand> getDailyTotalExpenseOfHousehold(Long householdId, LocalDate date) { // 월별 조회

        System.out.println("date = " + date.getMonth());
        System.out.println("date = " + date.getYear());

    /*
        private Integer expenseDayOfMonth;
        private BigDecimal dailyTotalExpense;
     */
        return queryFactory
                .select(
                        Projections.constructor(DailyTotalExpensesCommand.class,
                                expenseJpaEntity.expenseDate.dayOfMonth().as("expenseDayOfMonth"),
                                expenseJpaEntity.expenseAmount.sum().as("dailyTotalExpense")
                        )
                )
                .from(expenseJpaEntity)
                .join(expenseJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
                .where(
                        expenseJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        expenseJpaEntity.expenseDate.year().eq(date.getYear()),
                        expenseJpaEntity.expenseDate.month().eq(date.getMonthValue())
                )
                .groupBy(expenseJpaEntity.expenseDate.dayOfMonth())
                .fetch();
//        return queryFactory
//                .select(
//                        Projections.constructor(DailyTotalExpensesCommand.class,
//                                expenseJpaEntity.expenseDate.dayOfMonth().as("expenseDayOfMonth"),
//                                expenseJpaEntity.expenseAmount.sum().as("dailyTotalExpense")
//                        )
//                )
//                .from(expenseJpaEntity)
//                .join(expenseJpaEntity.spender, userJpaEntity)
//                .join(userJpaEntity.houseHoldJpaEntity, houseHoldJpaEntity)
//                .where(
//                        eqHouseholdId(householdId),
//                        expenseJpaEntity.expenseDate.year().eq(date.getYear()),
//                        expenseJpaEntity.expenseDate.month().eq(date.getMonthValue())
//                )
//                .groupBy(expenseJpaEntity.expenseDate.dayOfMonth())
//                .fetch();
    }

    public List<TotalExpenseByCategoryCommand> getTotalExpenseGroupByCategory(Long householdId, LocalDate from,
                                                                              LocalDate to) {
    return queryFactory
        .select(Projections.constructor(TotalExpenseByCategoryCommand.class,
            expenseJpaEntity.expenseCategory.as("expenseCategory"),
            expenseJpaEntity.expenseAmount.sum().as("totalExpenseAmount"))
        )
        .from(expenseJpaEntity)
        .join(expenseJpaEntity.houseHoldJpaEntity)
        .where(
                expenseJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                expenseJpaEntity.expenseDate.between(from, to)
        )
        .groupBy(expenseJpaEntity.expenseCategory)
        .fetch();

    }

    private BooleanExpression eqHouseholdId(Long householdId) {
        log.info("householdId : {}", householdId);
        return householdId != null ? userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId) : null;
    }

    private BooleanExpression betweenDate(LocalDateTime date) {
        log.info("startDate : {}, endDate : {}", date.plusDays(1).minusSeconds(1), date);
        return date != null ? expenseJpaEntity.expenseDate
                .between(date.toLocalDate(), date.toLocalDate()) : null;
    }

    private BooleanExpression betweenDate(LocalDate startDate, LocalDate endDate) {
        log.info("startDate : {}, endDate : {}", startDate, endDate);

        return (startDate != null) && (endDate != null) ? expenseJpaEntity.expenseDate.between(startDate, endDate) : null;
    }

    private BooleanExpression eqCategory(ExpenseCategory category) {
        return null;
//        return (category != null) ? settlementJpaEntity.expenseJpaEntity.expenseCategory.eq(category) : null;
    }

}
