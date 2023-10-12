package com.connect.accountApp.domain.virtualaccount.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.in.web.request.RegisterBillRequest;
import com.connect.accountApp.domain.bill.adapter.out.persistence.BillMapper;
import com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa.model.VirtualAccountJpaEntity;
import com.connect.accountApp.domain.virtualaccount.domain.model.VirtualAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VirtualAccountMapper {

  private final BillMapper billMapper;

  public VirtualAccount mapToDomain(VirtualAccountJpaEntity virtualAccountJpaEntity) {
    return VirtualAccount.builder()
        .virtualAccountId(virtualAccountJpaEntity.getVirtualAccountId())
        .bank(virtualAccountJpaEntity.getBank())
        .accountNumber(virtualAccountJpaEntity.getAccountNumber())
        .bill(billMapper.mapToDomainEntity(virtualAccountJpaEntity.getBillJpaEntity()))
        .build();
  }

  public VirtualAccountJpaEntity mapToJpaEntity(VirtualAccount virtualAccount) {
    return VirtualAccountJpaEntity.builder()
        .virtualAccountId(virtualAccount.getVirtualAccountId())
        .bank(virtualAccount.getBank())
        .accountNumber(virtualAccount.getAccountNumber())
        .billJpaEntity(billMapper.mapToJpaEntity(virtualAccount.getBill()))
        .build();
  }



}
