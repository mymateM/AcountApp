package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.UserQueryRepository;
import com.connect.accountApp.domain.user.application.port.out.FindHouseholdUserListPort;
import com.connect.accountApp.domain.user.application.port.out.GetRoommateSendMoneyPort;
import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.application.port.out.SaveUserPort;
import com.connect.accountApp.domain.user.application.port.out.command.RoommateSendMoneyCommand;
import com.connect.accountApp.domain.user.domain.model.User;
import com.connect.accountApp.domain.user.exception.UserNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements GetUserPort, GetRoommateSendMoneyPort,
    FindHouseholdUserListPort, SaveUserPort {

  private final UserJpaRepository userJpaRepository;
  private final UserQueryRepository userQueryRepository;
  private final UserMapper userMapper;
  private final HouseholdMapper householdMapper;

  @Override
  public User getUser(Long userId) {

    UserJpaEntity userJpaEntity = userJpaRepository.findById(userId).orElseThrow(
        () -> new UserNotFoundException("[userId] : "+userId +" 사용자가 존재하지 않습니다."));

    User user = userMapper.mapToDomainEntity(userJpaEntity);

    return user;
  }

  @Override
  public List<RoommateSendMoneyCommand> getRoommateSendMoney(Long householdId, Long userId) {
    return userQueryRepository.getRoommateSendMoney(householdId, userId);
  }


  @Override
  public List<User> findHouseholdUserList(Household household) {
    List<UserJpaEntity> userJpaEntities = userJpaRepository.findAllByHouseHoldJpaEntity(
        householdMapper.mapToJpaEntity(household));

    return userJpaEntities.stream()
        .map(userMapper::mapToDomainEntity).toList();
  }

  @Override
  public void save(User user) {
    UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
    userJpaRepository.save(userJpaEntity);
  }
}
