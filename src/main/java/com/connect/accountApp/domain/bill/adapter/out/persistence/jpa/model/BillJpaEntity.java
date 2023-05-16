package com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model;

import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillJpaEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long billId;
  private LocalDate billDate;
  private int billAmount;
  private String billImgUrl;

  @ManyToOne
  @JoinColumn(name = "household_id")
  private HouseHoldJpaEntity houseHoldJpaEntity;

  @Builder
  public BillJpaEntity(Long billId, LocalDate billDate, int billAmount, String billImgUrl,
      HouseHoldJpaEntity houseHoldJpaEntity) {
    this.billId = billId;
    this.billDate = billDate;
    this.billAmount = billAmount;
    this.billImgUrl = billImgUrl;
    this.houseHoldJpaEntity = houseHoldJpaEntity;
  }
}
