package com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "household")
public class HouseHoldJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long householdId;
  private String householdName;
  private LocalDate householdSettlementDate;
  private BigDecimal householdBudget;
  private Integer householdBudgetAllowanceRatio;
  private boolean settlementWillBeUpdated;

  @CreationTimestamp
  private LocalDateTime createdAt;
  private Integer householdSettlementDayOfMonth;

  @Column(unique = true)
  private String inviteCode;

  @Builder
  public HouseHoldJpaEntity(Long householdId, String householdName, LocalDate householdSettlementDate, BigDecimal householdBudget,
      Integer householdBudgetAllowanceRatio, boolean settlementWillBeUpdated, LocalDateTime createdAt, Integer householdSettlementDayOfMonth,
      String inviteCode) {
    this.householdId = householdId;
    this.householdName = householdName;
    this.householdSettlementDate = householdSettlementDate;
    this.householdBudget = householdBudget;
    this.householdBudgetAllowanceRatio = householdBudgetAllowanceRatio;
    this.settlementWillBeUpdated = settlementWillBeUpdated;
    this.createdAt = createdAt;
    this.householdSettlementDayOfMonth = householdSettlementDayOfMonth;
    this.inviteCode = inviteCode;
  }
}
