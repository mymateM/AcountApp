package com.connect.accountApp.domain.notification.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import com.connect.accountApp.domain.notification.domain.model.NotiCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
public class NotificationJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notiId;

  @Enumerated(EnumType.STRING)
  private NotiCategory notiCategory;
  private String notiContent;
  private boolean notiIsRead;
  private LocalDateTime notiCreatedAt;

  @OneToOne
  @JoinColumn(name = "expense_id")
  private ExpenseJpaEntity expenseJpaEntity;

  @OneToOne
  @JoinColumn(name = "bill_id")
  private BillJpaEntity billJpaEntity;

  private String senderName;

  @Builder
  public NotificationJpaEntity(Long notiId,
      NotiCategory notiCategory, String notiContent, boolean notiIsRead, LocalDateTime notiCreatedAt,
      ExpenseJpaEntity expenseJpaEntity,
      BillJpaEntity billJpaEntity,
      String senderName) {
    this.notiId = notiId;
    this.notiCategory = notiCategory;
    this.notiContent = notiContent;
    this.notiIsRead = notiIsRead;
    this.notiCreatedAt = notiCreatedAt;
    this.expenseJpaEntity = expenseJpaEntity;
    this.billJpaEntity = billJpaEntity;
    this.senderName = senderName;
  }
}
