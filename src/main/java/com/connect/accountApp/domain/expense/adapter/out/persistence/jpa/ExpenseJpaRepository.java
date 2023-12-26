package com.connect.accountApp.domain.expense.adapter.out.persistence.jpa;

import com.connect.accountApp.domain.expense.adapter.out.persistence.jpa.model.ExpenseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseJpaEntity, Long> {

}
