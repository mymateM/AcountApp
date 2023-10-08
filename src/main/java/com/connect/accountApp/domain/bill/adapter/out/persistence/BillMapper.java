package com.connect.accountApp.domain.bill.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillMapper {

  private final HouseholdMapper householdMapper;


  public Bill mapToDomainEntity(BillJpaEntity billJpaEntity) {

    if (billJpaEntity == null || billJpaEntity instanceof HibernateProxy) {
      return null;
    }

    return Bill.builder()
        .billId(billJpaEntity.getBillId())
        .billDate(billJpaEntity.getBillDate())
        .billPayment(billJpaEntity.getBillPayment())
        .billImgUrl(billJpaEntity.getBillImgUrl())
        .billStore(billJpaEntity.getBillStore())
        .household(householdMapper.mapToDomainEntity(billJpaEntity.getHouseHoldJpaEntity()))
        .build();
  }

  public BillJpaEntity mapToJpaEntity(Bill bill) {

    if (bill == null) {
      return null;
    }

    return BillJpaEntity.builder()
        .billId(bill.getBillId())
        .billDate(bill.getBillDate())
        .billPayment(bill.getBillPayment())
        .billImgUrl(bill.getBillImgUrl())
        .billStore(bill.getBillStore())
        .houseHoldJpaEntity(householdMapper.mapToJpaEntity(bill.getHousehold()))
        .build();
  }

}
