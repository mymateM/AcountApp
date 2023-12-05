package com.connect.accountApp.domain.user.adapter.out.persistence;

import com.connect.accountApp.domain.household.adapter.out.persistence.HouseholdMapper;
import com.connect.accountApp.domain.household.domain.model.Household;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.model.UserJpaEntity;
import com.connect.accountApp.domain.user.adapter.out.persistence.jpa.UserQueryRepository;
import com.connect.accountApp.domain.user.application.port.out.ExistsUserPort;
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
    FindHouseholdUserListPort, SaveUserPort, ExistsUserPort {

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
  public User findUser(String userEmail) {
    UserJpaEntity userJpaEntity = userJpaRepository.findByUserEmail(userEmail)
        .orElseThrow(
            () -> new UserNotFoundException("[userEmail] : " + userEmail + " 사용자가 존재하지 않습니다."));

    User user = userMapper.mapToDomainEntity(userJpaEntity);
    return user;
  }

  @Override
  public Boolean userExist(String email) {
    return userJpaRepository.existsByUserEmail(email);
  }
      
  public User findUserWithHousehold(String userEmail) {

    UserJpaEntity userJpaEntity = userQueryRepository.findUserJpaEntityWithHouseholdByEmail(userEmail);

    System.out.println(
            "userJpaEntity.getHouseHoldJpaEntity().getHouseholdId() = " + userJpaEntity.getHouseHoldJpaEntity()
                    .getHouseholdId());
    return userMapper.mapToDomainEntity(userJpaEntity);
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
  public List<User> findHouseholdMembers(Long householdId) {

    List<UserJpaEntity> householdJpaMember = userQueryRepository.getHouseholdMember(householdId);

    return householdJpaMember.stream()
        .map(userMapper::mapToDomainEntity).toList();
  }

  @Override
  public void save(User user) {
    UserJpaEntity userJpaEntity = userMapper.mapToJpaEntity(user);
    userJpaRepository.save(userJpaEntity);
  }

  @Override
  public Boolean existsUserEmail(String userEmail) {
    return userJpaRepository.existsByUserEmail(userEmail);
  }
}
