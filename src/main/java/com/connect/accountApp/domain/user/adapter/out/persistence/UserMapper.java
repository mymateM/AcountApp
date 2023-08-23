package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final HouseholdMapper householdMapper;

  public UserJpaEntity mapToJpaEntity(User user) {
    return UserJpaEntity.builder()
        .userId(user.getUserId())
        .userEmail(user.getUserEmail())
        .userPassword(user.getUserPassword())
        .userNickname(user.getUserNickname())
        .userImgUrl(user.getUserImgUrl())
        .userAccount(user.getUserAccount())
        .userAccountBank(user.getUserAccountBank())
        .userAccept(user.isUserAccept())
        .userRatio(user.getUserRatio())
        .role(user.getRole())
//        .houseHoldJpaEntity(householdMapper.mapToJpaEntity(user.getHousehold())) //todo 0823
        .build();
  }

  public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
    return User.builder()
        .userId(userJpaEntity.getUserId())
        .userEmail(userJpaEntity.getUserEmail())
        .userPassword(userJpaEntity.getUserPassword())
        .userNickname(userJpaEntity.getUserNickname())
        .userImgUrl(userJpaEntity.getUserImgUrl())
        .userAccount(userJpaEntity.getUserAccount())
        .userAccountBank(userJpaEntity.getUserAccountBank())
        .userAccept(userJpaEntity.isUserAccept())
        .userRatio(userJpaEntity.getUserRatio())
        .role(userJpaEntity.getRole())
        //.household(householdMapper.mapToDomainEntity(userJpaEntity.getHouseHoldJpaEntity()))
        .build();
  }
}
