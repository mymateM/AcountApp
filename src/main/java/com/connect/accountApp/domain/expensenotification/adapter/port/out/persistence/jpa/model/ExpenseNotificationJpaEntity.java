package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense_notification")
public class ExpenseNotificationJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Boolean isRead;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "expense_id")
  private ExpenseJpaEntity expenseJpaEntity;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserJpaEntity userJpaEntity;

  @Builder
  public ExpenseNotificationJpaEntity(Long id, Boolean isRead, LocalDateTime createdAt,
      ExpenseJpaEntity expenseJpaEntity, UserJpaEntity userJpaEntity) {

    this.id = id;
    this.isRead = isRead;
    this.createdAt = createdAt;
    this.expenseJpaEntity = expenseJpaEntity;
    this.userJpaEntity = userJpaEntity;
  }
}
