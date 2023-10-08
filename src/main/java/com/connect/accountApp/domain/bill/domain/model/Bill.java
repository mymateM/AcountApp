package com.connect.accountApp.domain.bill.domain.model;

import com.connect.accountApp.domain.household.domain.model.Household;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bill {

  private Long billId;
  private LocalDate billDate;
  private BigDecimal billPayment;
  private String billImgUrl;
  private Household household;
  private String billStore;
  private String billImage;

  @Builder
  public Bill(Long billId, LocalDate billDate, BigDecimal billPayment, String billImgUrl,
      Household household, String billStore, String billImage) {
    this.billId = billId;
    this.billDate = billDate;
    this.billPayment = billPayment;
    this.billImgUrl = billImgUrl;
    this.household = household;
    this.billStore = billStore;
    this.billImage = billImage;
  }
}
