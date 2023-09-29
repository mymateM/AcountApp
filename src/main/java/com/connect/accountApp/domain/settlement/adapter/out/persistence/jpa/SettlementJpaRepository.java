package com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa.model.SettlementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementJpaRepository extends JpaRepository<SettlementJpaEntity, Long> {

}
