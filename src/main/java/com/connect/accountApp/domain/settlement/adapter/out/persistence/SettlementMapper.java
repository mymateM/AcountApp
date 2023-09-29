package com.connect.accountApp.domain.settlement.adapter.out.persistence;

import com.connect.accountApp.domain.expense.adapter.out.persistence.ExpenseMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.UserMapper;
import com.connect.accountApp.domain.settlement.adapter.out.persistence.jpa.model.SettlementJpaEntity;
import com.connect.accountApp.domain.settlement.domain.model.Settlement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettlementMapper {

  private final ExpenseMapper expenseMapper;
  private final UserMapper userMapper;

  public Settlement mapToDomainEntity(SettlementJpaEntity settlementJpaEntity) {

    return Settlement.builder()
        .settlementId(settlementJpaEntity.getSettlementId())
        .expense(expenseMapper.mapToDomainEntity(settlementJpaEntity.getExpenseJpaEntity()))
        .user(userMapper.mapToDomainEntity(settlementJpaEntity.getUserJpaEntity()))
        .isSettlementDelegate(settlementJpaEntity.getIsSettlementDelegate())
        .build();
  }

  public SettlementJpaEntity mapToJpaEntity(Settlement settlement) {

    return SettlementJpaEntity.builder()
        .settlementId(settlement.getSettlementId())
        .expenseJpaEntity(expenseMapper.mapToJpaEntity(settlement.getExpense()))
        .userJpaEntity(userMapper.mapToJpaEntity(settlement.getUser()))
        .isSettlementDelegate(settlement.getIsSettlementDelegate())
        .build();
  }

}
