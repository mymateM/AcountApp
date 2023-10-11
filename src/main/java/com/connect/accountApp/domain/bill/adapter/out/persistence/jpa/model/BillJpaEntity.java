package com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bill")
public class BillJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long billId;
  private LocalDate billPaymentDate;
  private BigDecimal billPayment;
  private String billStore;
  private BillCategory billCategory;

  @OneToOne
  @JoinColumn("user_id")
  private UserJpaEntity billRegisterJpaEntity;

  @ManyToOne
  @JoinColumn("household_id")
  private HouseHoldJpaEntity houseHoldJpaEntity;

  @Builder
  public BillJpaEntity(Long billId, LocalDate billPaymentDate, BigDecimal billPayment,
      String billStore, BillCategory billCategory,
      UserJpaEntity billRegisterJpaEntity, HouseHoldJpaEntity houseHoldJpaEntity) {
    this.billId = billId;
    this.billPaymentDate = billPaymentDate;
    this.billPayment = billPayment;
    this.billStore = billStore;
    this.billCategory = billCategory;
    this.billRegisterJpaEntity = billRegisterJpaEntity;
    this.houseHoldJpaEntity = houseHoldJpaEntity;
  }
}
