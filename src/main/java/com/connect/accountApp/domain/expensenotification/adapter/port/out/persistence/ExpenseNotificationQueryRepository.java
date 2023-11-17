package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence;

import static com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.QExpenseJpaEntity.expenseJpaEntity;
import static com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model.QExpenseNotificationJpaEntity.expenseNotificationJpaEntity;

import com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model.ExpenseNotificationJpaEntity;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.ExpenseNotificationCommand;
import com.connect.accountApp.domain.expensenotification.application.port.in.command.FindSpenderCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ExpenseNotificationQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<ExpenseNotificationCommand> findExpenseNotificationsInHousehold(String userEmail) {

    System.out.println("userEmail = " + userEmail);
    return jpaQueryFactory
        .select(Projections.constructor(ExpenseNotificationCommand.class,
            expenseNotificationJpaEntity.id.as("expenseNotificationId"),
            expenseNotificationJpaEntity.expenseJpaEntity.expenseId.as("expenseId"),
            expenseNotificationJpaEntity.expenseJpaEntity.expenseCategory.as("expenseCategory"),
            expenseNotificationJpaEntity.createdAt,
            expenseNotificationJpaEntity.isRead,
            expenseNotificationJpaEntity.expenseJpaEntity.expenseAmount
        ))
        .from(expenseNotificationJpaEntity)
        .join(expenseNotificationJpaEntity.userJpaEntity)
        .join(expenseNotificationJpaEntity.expenseJpaEntity)
        .where(
            expenseNotificationJpaEntity.userJpaEntity.userEmail.eq(userEmail)
        )
        .orderBy(expenseNotificationJpaEntity.id.asc())
        .fetch();
  }

  public List<FindSpenderCommand> findSpender(List<Long> expenseIds) {
      return null;
//
//    return jpaQueryFactory
//        .select(
//            Projections.constructor(FindSpenderCommand.class,
//            settlementJpaEntity.expenseJpaEntity.expenseId,
//            settlementJpaEntity.userJpaEntity.userNickname.as("spenderName")
//            )
//        )
//        .from(settlementJpaEntity)
//        .join(settlementJpaEntity.expenseJpaEntity, expenseJpaEntity)
//        .where(
//            settlementJpaEntity.expenseJpaEntity.expenseId.in(expenseIds),
//            settlementJpaEntity.isSettlementDelegate.isTrue()
//        )
//        .orderBy()
//        .fetch();
  }

  public List<ExpenseNotificationJpaEntity> findExpenseNotifications(List<Long> expenseNotificationIds) {

    return jpaQueryFactory
        .select(expenseNotificationJpaEntity)
        .from(expenseNotificationJpaEntity)
        .where(
            expenseNotificationJpaEntity.id.in(expenseNotificationIds)
        )
        .fetch();
  }

}
