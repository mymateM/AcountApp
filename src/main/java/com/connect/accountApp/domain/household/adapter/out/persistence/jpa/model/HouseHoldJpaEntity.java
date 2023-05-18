package com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "household")
public class HouseHoldJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long householdId;
  private LocalDate householdSettlementDate;
  private int householdBudget;
  private boolean householdAccept;

  @Builder
  public HouseHoldJpaEntity(Long householdId, LocalDate householdSettlementDate,
      int householdBudget,
      boolean householdAccept) {
    this.householdId = householdId;
    this.householdSettlementDate = householdSettlementDate;
    this.householdBudget = householdBudget;
    this.householdAccept = householdAccept;
  }
}
