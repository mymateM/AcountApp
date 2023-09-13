package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

  List<UserJpaEntity> findAllByHouseHoldJpaEntity(HouseHoldJpaEntity jpaEntity);

  Optional<UserJpaEntity> findByUserEmail(String userEmail);

  Boolean existsByUserEmail(String userEmail);
}
