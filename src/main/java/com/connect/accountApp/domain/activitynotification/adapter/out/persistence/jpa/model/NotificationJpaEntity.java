package com.connect.accountApp.domain.activitynotification.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.activitynotification.domain.model.NotiCategory;
import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
public class NotificationJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityNotificationId;

  @Enumerated(EnumType.STRING)
  private NotiCategory activityNotificationCategory;

  private String title;
  private String message;
  private boolean isRead;

  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;

  @OneToOne
  @JoinColumn(name = "bill_id")
  private BillJpaEntity billJpaEntity;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity requesterJpaEntity;

  @Builder
  public NotificationJpaEntity(Long activityNotificationId,
      NotiCategory activityNotificationCategory, String title, String message, boolean isRead,
      LocalDateTime createdAt, LocalDateTime modifiedAt,
      BillJpaEntity billJpaEntity,
      UserJpaEntity requesterJpaEntity) {
    this.activityNotificationId = activityNotificationId;
    this.activityNotificationCategory = activityNotificationCategory;
    this.title = title;
    this.message = message;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.billJpaEntity = billJpaEntity;
    this.requesterJpaEntity = requesterJpaEntity;
  }
}
