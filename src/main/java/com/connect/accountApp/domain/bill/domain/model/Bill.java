package com.connect.accountApp.domain.bill.domain.model;

import com.connect.accountApp.domain.household.domain.model.Household;
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
  private int billAmount;
  private String billImgUrl;
  private Household household;

  @Builder
  public Bill(Long billId, LocalDate billDate, int billAmount, String billImgUrl,
      Household household) {
    this.billId = billId;
    this.billDate = billDate;
    this.billAmount = billAmount;
    this.billImgUrl = billImgUrl;
    this.household = household;
  }
}
