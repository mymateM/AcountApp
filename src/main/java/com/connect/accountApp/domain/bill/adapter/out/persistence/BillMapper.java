package com.connect.accountApp.domain.bill.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillMapper {

  private final HouseholdMapper householdMapper;
  private final UserMapper userMapper;

  public Bill mapToDomainEntity(BillJpaEntity billJpaEntity) {

    if (billJpaEntity == null || billJpaEntity instanceof HibernateProxy) {
      return null;
    }

    return Bill.builder()
        .billId(billJpaEntity.getBillId())
        .billPaymentDate(billJpaEntity.getBillPaymentDate())
        .billPayment(billJpaEntity.getBillPayment())
        .billCategory(billJpaEntity.getBillCategory())
        .billStore(billJpaEntity.getBillStore())
        .billRegister(getUser(billJpaEntity.getBillRegisterJpaEntity()))
        .household(getHousehold(billJpaEntity.getHouseHoldJpaEntity()))
        .build();
  }

  public BillJpaEntity mapToJpaEntity(Bill bill) {

    if (bill == null) {
      return null;
    }

    return BillJpaEntity.builder()
        .billId(bill.getBillId())
        .billPaymentDate(bill.getBillPaymentDate())
        .billPayment(bill.getBillPayment())
        .billCategory(bill.getBillCategory())
        .billStore(bill.getBillStore())
        .billRegisterJpaEntity(userMapper.mapToJpaEntity(bill.getBillRegister()))
        .houseHoldJpaEntity(householdMapper.mapToJpaEntity(bill.getHousehold()))
        .build();
  }

  private User getUser(UserJpaEntity userJpaEntity) {
    if (userJpaEntity == null || userJpaEntity instanceof HibernateProxy) {
      return null;
    }
    else
      return userMapper.mapToDomainEntity(userJpaEntity);
  }

  private Household getHousehold(HouseHoldJpaEntity houseHoldJpaEntity) {
    if (houseHoldJpaEntity == null || houseHoldJpaEntity instanceof HibernateProxy) {
      return null;
    }
    else
      return householdMapper.mapToDomainEntity(houseHoldJpaEntity);
  }

}
