package com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  private LocalDate billDate;
  private BigDecimal billPayment;
  private String billImgUrl;
  private String billStore;

  @ManyToOne
  @JoinColumn(name = "household_id")
  private HouseHoldJpaEntity houseHoldJpaEntity;

  @Builder
  public BillJpaEntity(Long billId, LocalDate billDate, BigDecimal billPayment, String billImgUrl,
      HouseHoldJpaEntity houseHoldJpaEntity, String billStore) {
    this.billId = billId;
    this.billDate = billDate;
    this.billPayment = billPayment;
    this.billImgUrl = billImgUrl;
    this.houseHoldJpaEntity = houseHoldJpaEntity;
    this.billStore = billStore;
  }
}
