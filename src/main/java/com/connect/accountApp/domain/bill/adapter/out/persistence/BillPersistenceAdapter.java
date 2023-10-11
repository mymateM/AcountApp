package com.connect.accountApp.domain.bill.adapter.out.persistence;

import com.connect.accountApp.domain.bill.application.port.command.BillCommand;
import com.connect.accountApp.domain.bill.application.port.out.FindBillPort;
import com.connect.accountApp.domain.bill.domain.model.BillCategory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillPersistenceAdapter implements FindBillPort {

  private final BillQueryRepository billQueryRepository;
  private final BillMapper billMapper;

  @Override
  public List<BillCommand> findBills(Long householdId, BillCategory billCategory) {
    return billQueryRepository.findBills(householdId, billCategory);
  }
}
