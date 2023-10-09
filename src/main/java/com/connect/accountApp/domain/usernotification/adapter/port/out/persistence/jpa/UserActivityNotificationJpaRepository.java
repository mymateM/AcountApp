package com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa;

import com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model.UserActivityNotificationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityNotificationJpaRepository extends JpaRepository<UserActivityNotificationJpaEntity, Long> {


  @Override
  <S extends UserActivityNotificationJpaEntity> S save(S entity);
}
