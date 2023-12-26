package com.connect.accountApp.domain.bill.adapter.out.persistence;

import static com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.QBillJpaEntity.billJpaEntity;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.command.RecentBillCategoryCommand;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BillQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;


  public List<BillCommand> findBills(Long householdId, BillCategory billCategory) {

    return jpaQueryFactory
        .select(Projections.constructor(BillCommand.class,
            billJpaEntity.billId,
            billJpaEntity.billCategory,
            billJpaEntity.billPaymentDate,
            billJpaEntity.billStore,
            billJpaEntity.billPayment
        ))
        .from(billJpaEntity)
        .where(
            billJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
            billJpaEntity.billCategory.eq(billCategory)
        )
        .fetch();

  }

    public RecentBillCategoryCommand findRecentBillCategory(Long householdId, BillCategory billCategory) {

        return jpaQueryFactory
                .select(Projections.constructor(RecentBillCategoryCommand.class,
                        billJpaEntity.billCategory,
                        billJpaEntity.billPaymentDate,
                        billJpaEntity.billPayment.as("billPaymentAmount")
                ))
                .from(billJpaEntity)
                .where(
                        billJpaEntity.houseHoldJpaEntity.householdId.eq(householdId),
                        billJpaEntity.billCategory.eq(billCategory)
                )
                .orderBy(billJpaEntity.billPaymentDate.desc())
                .limit(1)
                .fetchOne();

    }

}
