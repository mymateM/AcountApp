package com.connect.accountApp.domain.bill.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillJpaRepository extends JpaRepository<BillJpaEntity, Long> {

  @Override
  <S extends BillJpaEntity> S save(S entity);

  @Override
  Optional<BillJpaEntity> findById(Long aLong);
}
