package com.connect.accountApp.domain.bill.adapter.out.persistence;

import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.BillJpaRepository;
import com.connect.accountApp.domain.bill.adapter.out.persistence.jpa.model.BillJpaEntity;
import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.out.FindBillPort;
import com.connect.accountApp.domain.bill.application.port.out.SaveBillPort;
import com.connect.accountApp.domain.bill.domain.model.Bill;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import com.connect.accountApp.domain.bill.exception.NotFoundBillException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillPersistenceAdapter implements FindBillPort, SaveBillPort {

  private final BillQueryRepository billQueryRepository;
  private final BillJpaRepository billJpaRepository;
  private final BillMapper billMapper;

  @Override
  public List<BillCommand> findBills(Long householdId, BillCategory billCategory) {
    return billQueryRepository.findBills(householdId, billCategory);
  }

  @Override
  public Bill findBill(Long billId) {
    BillJpaEntity billJpaEntity = billJpaRepository.findById(billId)
        .orElseThrow(() -> new NotFoundBillException("[billId] " + billId + "에 해당하는 고지서가 존재하지 않습니다."));
    return billMapper.mapToDomainEntity(billJpaEntity);
  }

  @Override
  public Bill saveBill(Bill newBill) {
    BillJpaEntity billJpaEntity = billMapper.mapToJpaEntity(newBill);
    BillJpaEntity savedBillJpaEntity = billJpaRepository.save(billJpaEntity);
    return billMapper.mapToDomainEntity(savedBillJpaEntity);
  }
}
