package com.connect.accountApp.domain.scheduler.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.scheduler.adapter.out.persistence.jpa.model.SchedulerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerJpaRepository extends JpaRepository<SchedulerJpaEntity, Long> {

  SchedulerJpaEntity findBySchedulerIdAndSchedulerName(String scheduler_idx, String scheduler_name);

}
