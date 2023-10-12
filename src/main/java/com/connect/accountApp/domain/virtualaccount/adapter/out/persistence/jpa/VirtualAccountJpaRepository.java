package com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa.model.VirtualAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualAccountJpaRepository extends JpaRepository<VirtualAccountJpaEntity, Long> {

}
