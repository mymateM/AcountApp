package com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityNotificationJpaRepository extends JpaRepository<ActivityNotificationJpaEntity, Long> {
}
