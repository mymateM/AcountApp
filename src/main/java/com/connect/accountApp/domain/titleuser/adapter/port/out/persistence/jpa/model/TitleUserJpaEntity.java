package com.connect.accountApp.domain.titleuser.adapter.port.out.persistence.jpa.model;

import com.connect.accountApp.domain.title.adapter.out.persistence.jpa.model.TitleJpaEntity;
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
@Table(name = "titleuser")
public class TitleUserJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long titleUserId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "title_id")
  private TitleJpaEntity titleJpaEntity;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @Builder
  public TitleUserJpaEntity(Long titleUserId,
      UserJpaEntity userJpaEntity,
      TitleJpaEntity titleJpaEntity, LocalDateTime createdAt) {
    this.titleUserId = titleUserId;
    this.userJpaEntity = userJpaEntity;
    this.titleJpaEntity = titleJpaEntity;
    this.createdAt = createdAt;
  }
}
