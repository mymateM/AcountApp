package com.connect.accountApp.domain.virtualaccount.adapter.out.persistence;

import com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa.VirtualAccountJpaRepository;
import com.connect.accountApp.domain.virtualaccount.adapter.out.persistence.jpa.model.VirtualAccountJpaEntity;
import com.connect.accountApp.domain.virtualaccount.application.port.out.SaveVirtualAccountPort;
import com.connect.accountApp.domain.virtualaccount.domain.model.VirtualAccount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VirtualAccountPersistenceAdapter implements SaveVirtualAccountPort {

  private final VirtualAccountJpaRepository virtualAccountJpaRepository;
  private final VirtualAccountMapper virtualAccountMapper;

  @Override
  public List<VirtualAccount> saveAll(List<VirtualAccount> newVirtualAccounts) {
    List<VirtualAccountJpaEntity> newVirtualAccountJpaEntities = newVirtualAccounts.stream()
        .map(virtualAccountMapper::mapToJpaEntity).toList();

    return virtualAccountJpaRepository.saveAll(newVirtualAccountJpaEntities)
        .stream().map(virtualAccountMapper::mapToDomain).toList();
  }
}
