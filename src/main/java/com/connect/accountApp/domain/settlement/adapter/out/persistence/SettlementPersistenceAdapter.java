package com.connect.accountApp.domain.settlement.adapter.out.persistence;

import com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa.SettlementJpaRepository;
import com.connect.accountApp.domain.settlement.application.port.out.FindSettlementPort;
import com.connect.accountApp.domain.settlement.application.port.out.SaveSettlementPort;
import com.connect.accountApp.domain.settlement.application.port.out.command.ExpenseOfHouseholdCommand;
import com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa.model.SettlementJpaEntity;
import com.connect.accountApp.domain.settlement.domain.model.Settlement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SettlementPersistenceAdapter implements SaveSettlementPort, FindSettlementPort {

  private final SettlementJpaRepository settlementJpaRepository;
  private final SettlementQueryRepository settlementQueryRepository;
  private final SettlementMapper settlementMapper;

  @Override
  public Long saveSettlement(Settlement settlement) {
    SettlementJpaEntity settlementJpaEntity = settlementMapper.mapToJpaEntity(settlement);
    return settlementJpaRepository.save(settlementJpaEntity).getSettlementId();
  }

  @Override
  public List<BigDecimal> findUserRealExpense(String userEmail, LocalDate startDate,
      LocalDate endDate) {

    return settlementQueryRepository.findUserRealExpense(userEmail, startDate, endDate);
  }

  @Override
  public List<ExpenseOfHouseholdCommand> findHouseholdExpenses(Long householdId,
      LocalDate startDate, LocalDate endDate) {

    return settlementQueryRepository.findExpenseOfHousehold(householdId, startDate, endDate);
  }
}
