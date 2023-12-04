package com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa;

import com.connect.accountApp.domain.expensenotification.adapter.port.out.persistence.jpa.model.ExpenseNotificationJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseNotificationJpaRepository extends JpaRepository<ExpenseNotificationJpaEntity, Long> {

  @Override
  <S extends ExpenseNotificationJpaEntity> List<S> saveAll(Iterable<S> entities);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);
}
