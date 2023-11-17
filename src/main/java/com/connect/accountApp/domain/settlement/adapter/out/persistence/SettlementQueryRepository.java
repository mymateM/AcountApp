package com.connect.accountApp.domain.settlement.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.core.types.dsl.Expressions.list;

import com.connect.accountApp.domain.settlement.application.port.out.command.ExpenseOfHouseholdCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SettlementQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BigDecimal findUserRealExpense(Long userId, LocalDate startDate, LocalDate endDate) {

        return queryFactory
                .select(expenseJpaEntity.expenseAmount)
                .from(expenseJpaEntity)
                .join(expenseJpaEntity, expenseJpaEntity)
                .join(expenseJpaEntity.spender, userJpaEntity)
                .where(
                        expenseJpaEntity.expenseDate.between(startDate, endDate),
                        expenseJpaEntity.spender.userId.eq(userId)

                        )
                .fetchOne();
    }

    public List<ExpenseOfHouseholdCommand> findExpenseOfHousehold(Long householdId, LocalDate startDate,
                                                                  LocalDate endDate) {

        return null;
//    return queryFactory
//        .from(settlementJpaEntity)
//        .join(settlementJpaEntity.expenseJpaEntity, expenseJpaEntity)
//        .join(settlementJpaEntity.userJpaEntity, userJpaEntity)
//        .where(
//            settlementJpaEntity.expenseJpaEntity.expenseDate.between(startDate, endDate),
//            settlementJpaEntity.userJpaEntity.houseHoldJpaEntity.householdId.eq(householdId)
//        )
//        .transform(
//            groupBy(settlementJpaEntity.expenseJpaEntity).list(
//                Projections.fields(ExpenseOfHouseholdCommand.class,
//                settlementJpaEntity.expenseJpaEntity.expenseId.as("expenseId"),
//                settlementJpaEntity.expenseJpaEntity.expenseAmount.as("expenseAmount"),
//                list(
//                    Projections.fields(ExpenseOfHouseholdCommand.ExpenseRatioOfUser.class,
//                        settlementJpaEntity.userJpaEntity.userId.as("userId"),
//                        settlementJpaEntity.userJpaEntity.userRatio.as("userExpenseRatio")
//                    )
//                ).as("expenseRatioOfUsers")
//                )
//            ));
    }

}
