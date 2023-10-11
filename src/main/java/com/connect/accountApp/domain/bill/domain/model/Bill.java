package com.connect.accountApp.domain.bill.domain.model;

import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.domain.model.User;
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
  private LocalDate billPaymentDate;
  private BigDecimal billPayment;
  private String billStore;
  private BillCategory billCategory;

  private User billRegister;
  private Household household;

  @Builder
  public Bill(Long billId, LocalDate billPaymentDate, BigDecimal billPayment, String billStore,
      BillCategory billCategory, User billRegister, Household household) {
    this.billId = billId;
    this.billPaymentDate = billPaymentDate;
    this.billPayment = billPayment;
    this.billStore = billStore;
    this.billCategory = billCategory;
    this.billRegister = billRegister;
    this.household = household;
  }
}
