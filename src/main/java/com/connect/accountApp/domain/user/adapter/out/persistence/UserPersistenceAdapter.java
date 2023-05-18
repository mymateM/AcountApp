package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements GetUserPort {

  private final UserJpaRepository userJpaRepository;
  private final UserMapper userMapper;

  @Override
  public User getUser(Long userId) {

    UserJpaEntity userJpaEntity = userJpaRepository.findById(userId).orElseThrow(
        () -> new UserNotFoundException("[userId] : "+userId +" 사용자가 존재하지 않습니다."));

    User user = userMapper.mapToDomainEntity(userJpaEntity);

    return user;
  }
}
