package com.connect.accountApp.domain.bill.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillMapper {

  private final HouseholdMapper householdMapper;


  public Bill mapToDomainEntity(BillJpaEntity billJpaEntity) {
    return Bill.builder()
        .billId(billJpaEntity.getBillId())
        .billDate(billJpaEntity.getBillDate())
        .billAmount(billJpaEntity.getBillAmount())
        .billImgUrl(billJpaEntity.getBillImgUrl())
        .household(householdMapper.mapToDomainEntity(billJpaEntity.getHouseHoldJpaEntity()))
        .build();
  }

  public BillJpaEntity mapToJpaEntity(Bill bill) {
    return BillJpaEntity.builder()
        .billId(bill.getBillId())
        .billDate(bill.getBillDate())
        .billAmount(bill.getBillAmount())
        .billImgUrl(bill.getBillImgUrl())
        .houseHoldJpaEntity(householdMapper.mapToJpaEntity(bill.getHousehold()))
        .build();
  }

}
