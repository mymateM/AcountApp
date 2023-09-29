package com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "settlement")
public class SettlementJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long settlementId;

  @ManyToOne
  @JoinColumn(name = "expense_id")
  private ExpenseJpaEntity expenseJpaEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  private Boolean isSettlementDelegate;

  @Builder
  public SettlementJpaEntity(Long settlementId,
      ExpenseJpaEntity expenseJpaEntity,
      UserJpaEntity userJpaEntity, Boolean isSettlementDelegate) {
    this.settlementId = settlementId;
    this.expenseJpaEntity = expenseJpaEntity;
    this.userJpaEntity = userJpaEntity;
    this.isSettlementDelegate = isSettlementDelegate;
  }
}
