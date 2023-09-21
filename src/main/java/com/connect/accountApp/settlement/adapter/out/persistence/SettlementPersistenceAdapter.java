package com.connect.accountApp.settlement.adapter.out.persistence;

import com.connect.accountApp.settlement.adapter.out.persistence.jpa.SettlementJpaRepository;
import com.connect.accountApp.settlement.adapter.out.persistence.jpa.model.SettlementJpaEntity;
import com.connect.accountApp.settlement.application.port.out.SaveSettlementPort;
import com.connect.accountApp.settlement.domain.model.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SettlementPersistenceAdapter implements SaveSettlementPort {

  private final SettlementJpaRepository settlementJpaRepository;
  private final SettlementMapper settlementMapper;

  @Override
  public Long saveSettlement(Settlement settlement) {
    SettlementJpaEntity settlementJpaEntity = settlementMapper.mapToJpaEntity(settlement);
    return settlementJpaRepository.save(settlementJpaEntity).getSettlementId();
  }
}
