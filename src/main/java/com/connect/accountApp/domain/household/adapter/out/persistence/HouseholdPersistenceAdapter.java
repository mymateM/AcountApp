package com.connect.accountApp.domain.household.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.application.port.out.GetHouseholdPort;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.household.exception.HouseholdNotFoundException;
import com.connect.accountApp.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseholdPersistenceAdapter implements GetHouseholdPort {

  private final HouseholdJpaRepository householdJpaRepository;
  private final HouseholdMapper householdMapper;

  @Override
  public Household getHousehold(Long householdId) {

    HouseHoldJpaEntity houseHoldJpaEntity = householdJpaRepository.findById(householdId)
        .orElseThrow(() -> new HouseholdNotFoundException("household: " + householdId + "인 가구가 없습니다.", ErrorCode.HOUSEHOLD_NOT_FOUND));

    Household household = householdMapper.mapToDomainEntity(houseHoldJpaEntity);
    return household;
  }
}
