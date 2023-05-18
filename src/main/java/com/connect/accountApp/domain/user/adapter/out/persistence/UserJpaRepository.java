package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {



}
