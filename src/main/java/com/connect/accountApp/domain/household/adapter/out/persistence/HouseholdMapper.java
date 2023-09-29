package com.connect.accountApp.domain.household.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.domain.model.Household;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMapper {

  public Household mapToDomainEntity(HouseHoldJpaEntity houseHoldJpaEntity) {
    return Household.builder()
        .householdId(houseHoldJpaEntity.getHouseholdId())
        .householdName(houseHoldJpaEntity.getHouseholdName())
        .householdSettlementDate(houseHoldJpaEntity.getHouseholdSettlementDate())
        .householdBudget(houseHoldJpaEntity.getHouseholdBudget())
        .householdBudgetAllowanceRatio(houseHoldJpaEntity.getHouseholdBudgetAllowanceRatio())
        .settlementWillBeUpdated(houseHoldJpaEntity.isSettlementWillBeUpdated())
        .createdAt(houseHoldJpaEntity.getCreatedAt())
        .householdSettlementDayOfMonth(houseHoldJpaEntity.getHouseholdSettlementDayOfMonth())
        .inviteCode(houseHoldJpaEntity.getInviteCode())
        .build();
  }

  public HouseHoldJpaEntity mapToJpaEntity(Household household) {
    return HouseHoldJpaEntity.builder()
        .householdId(household.getHouseholdId())
        .householdName(household.getHouseholdName())
        .householdSettlementDate(household.getHouseholdSettlementDate())
        .householdBudget(household.getHouseholdBudget())
        .householdBudgetAllowanceRatio(household.getHouseholdBudgetAllowanceRatio())
        .settlementWillBeUpdated(household.isSettlementWillBeUpdated())
        .createdAt(household.getCreatedAt())
        .householdSettlementDayOfMonth(household.getHouseholdSettlementDayOfMonth())
        .inviteCode(household.getInviteCode())
        .build();
  }
}
