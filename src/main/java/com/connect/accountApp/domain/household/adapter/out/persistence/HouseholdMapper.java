package com.connect.accountApp.domain.household.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.domain.model.Household;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMapper {

  public Household mapToDomainEntity(HouseHoldJpaEntity houseHoldJpaEntity) {
    return Household.builder()
        .householdId(houseHoldJpaEntity.getHouseholdId())
        .householdSettlementDate(houseHoldJpaEntity.getHouseholdSettlementDate())
        .householdBudget(houseHoldJpaEntity.getHouseholdBudget())
        .householdAccept(houseHoldJpaEntity.isHouseholdAccept())
        .build();
  }

  public HouseHoldJpaEntity mapToJpaEntity(Household household) {
    return HouseHoldJpaEntity.builder()
        .householdId(household.getHouseholdId())
        .householdSettlementDate(household.getHouseholdSettlementDate())
        .householdBudget(household.getHouseholdBudget())
        .householdAccept(household.isHouseholdAccept())
        .build();
  }
}
