package com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.expense.domain.model.ExpenseCategory;
import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense")
public class ExpenseJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long expenseId;
  private BigDecimal expenseAmount;
  private LocalDate expenseDate;
  private String expenseStore;
  private String expenseMemo;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @Enumerated(EnumType.STRING)
  private ExpenseCategory expenseCategory;

  @Builder
  public ExpenseJpaEntity(Long expenseId, BigDecimal expenseAmount, LocalDate expenseDate,
      String expenseStore, String expenseMemo,
      UserJpaEntity userJpaEntity,
      ExpenseCategory expenseCategory) {
    this.expenseId = expenseId;
    this.expenseAmount = expenseAmount;
    this.expenseDate = expenseDate;
    this.expenseStore = expenseStore;
    this.expenseMemo = expenseMemo;
    this.userJpaEntity = userJpaEntity;
    this.expenseCategory = expenseCategory;
  }
}
