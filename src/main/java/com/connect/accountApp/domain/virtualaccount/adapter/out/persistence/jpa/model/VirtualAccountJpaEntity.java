package com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.user.domain.model.Bank;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "virtual_account")
public class VirtualAccountJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long virtualAccountId;

  @Enumerated(EnumType.STRING)
  private Bank bank;

  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "bill_id")
  private BillJpaEntity billJpaEntity;

  @Builder
  public VirtualAccountJpaEntity(Long virtualAccountId,
      Bank bank, String accountNumber,
      BillJpaEntity billJpaEntity) {
    this.virtualAccountId = virtualAccountId;
    this.bank = bank;
    this.accountNumber = accountNumber;
    this.billJpaEntity = billJpaEntity;
  }
}
