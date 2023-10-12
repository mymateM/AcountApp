package com.connect.accountApp.domain.virtualaccount.domain.model;

import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.user.domain.model.Bank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VirtualAccount {

  private Long virtualAccountId;
  private Bank bank;
  private String accountNumber;

  private Bill bill;

  @Builder
  public VirtualAccount(Long virtualAccountId,
      Bank bank, String accountNumber, Bill bill) {
    this.virtualAccountId = virtualAccountId;
    this.bank = bank;
    this.accountNumber = accountNumber;
    this.bill = bill;
  }
}
