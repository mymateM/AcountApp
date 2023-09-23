package com.connect.accountApp.settlement.adapter.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.QUserJpaEntity.userJpaEntity;
import static com.connect.accountApp.settlement.adapter.out.persistence.jpa.model.QSettlementJpaEntity.settlementJpaEntity;

import com.connect.accountApp.settlement.adapter.out.persistence.jpa.model.QSettlementJpaEntity;
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

  public List<BigDecimal> findUserRealExpense(String userEmail, LocalDate startDate,
      LocalDate endDate) {

    return queryFactory
        .select(settlementJpaEntity.expenseJpaEntity.expenseAmount)
        .from(settlementJpaEntity)
        .join(settlementJpaEntity.expenseJpaEntity, expenseJpaEntity)
        .join(settlementJpaEntity.userJpaEntity, userJpaEntity)
        .where(
            settlementJpaEntity.expenseJpaEntity.expenseDate.between(startDate, endDate),
            settlementJpaEntity.isSettlementDelegate.isTrue(),
            settlementJpaEntity.userJpaEntity.userEmail.eq(userEmail)
        )
        .fetch();

  }
}
