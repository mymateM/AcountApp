package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.household.adapter.out.persistence.jpa.model.HouseHoldJpaEntity;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
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
        .deviceToken(user.getDeviceToken())
        .houseHoldJpaEntity(getHouseHoldJpaEntityOfUser(user.getHousehold()))
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
        .deviceToken(userJpaEntity.getDeviceToken())
        .household(getHouseHoldOfUserJpaEntity(userJpaEntity.getHouseHoldJpaEntity()))
        .build();
  }

  private HouseHoldJpaEntity getHouseHoldJpaEntityOfUser(Household household) {
    if (household == null) {
      return null;
    }else {
      return householdMapper.mapToJpaEntity(household);
    }
  }

  private Household getHouseHoldOfUserJpaEntity(HouseHoldJpaEntity houseHoldJpaEntity) {

    if (houseHoldJpaEntity == null || houseHoldJpaEntity instanceof HibernateProxy) {
      System.out.println("hello1");
      return null;
    }else {
      return householdMapper.mapToDomainEntity(houseHoldJpaEntity);
    }
  }
}
