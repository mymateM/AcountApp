package com.connect.accountApp.domain.titleuser.adapter.port.out.persistence.jpa.model;

import com.connect.accountApp.domain.title.domain.model.Title;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "title_user")
public class TitleUserJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long titleUserId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  private Title title;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Builder
  public TitleUserJpaEntity(Long titleUserId,
      UserJpaEntity userJpaEntity, Title title, LocalDateTime createdAt) {
    this.titleUserId = titleUserId;
    this.userJpaEntity = userJpaEntity;
    this.title = title;
    this.createdAt = createdAt;
  }
}
