package com.connect.accountApp.domain.household.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdJpaRepository extends JpaRepository<HouseHoldJpaEntity, Long> {

}
