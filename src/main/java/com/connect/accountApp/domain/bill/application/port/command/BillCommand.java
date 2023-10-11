package com.connect.accountApp.domain.bill.application.port.command;

import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillCommand {

  private Long billId;
  private String billImageUrl;
  private LocalDate billPaymentDate;
  private String billStore;
  private BigDecimal billPaymentAmount;

  public BillCommand(Long billId, BillCategory billCategory, LocalDate billPaymentDate,
      String billStore, BigDecimal billPaymentAmount) {
    this.billId = billId;
    this.billImageUrl = billCategory.getImgUrl();
    this.billPaymentDate = billPaymentDate;
    this.billStore = billStore;
    this.billPaymentAmount = billPaymentAmount;
  }
}
