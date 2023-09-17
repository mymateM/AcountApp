package com.connect.accountApp.domain.usernotification.adapter.port.out.persistence.jpa.model;

import com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model.ActivityNotificationJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_notification")
public class UserActivityNotificationJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userNotiId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @ManyToOne
  @JoinColumn(name = "noti_id")
  private ActivityNotificationJpaEntity activityNotificationJpaEntity;

  @Builder
  public UserActivityNotificationJpaEntity(Long userNotiId,
      UserJpaEntity userJpaEntity,
      ActivityNotificationJpaEntity activityNotificationJpaEntity) {
    this.userNotiId = userNotiId;
    this.userJpaEntity = userJpaEntity;
    this.activityNotificationJpaEntity = activityNotificationJpaEntity;
  }
}
