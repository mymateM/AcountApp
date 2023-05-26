package com.connect.accountApp.domain.titleuser.adapter.port.out.persistence;

import com.connect.accountApp.domain.titleuser.application.port.out.FindUserTitlePort;
import com.connect.accountApp.domain.titleuser.application.port.out.command.UserTitleCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TitleUserPersistenceAdapter implements FindUserTitlePort {

  private final TitleUserQueryRepository titleUserQueryRepository;

  @Override
  public UserTitleCommand findUserTitle(Long userId) {
    return titleUserQueryRepository.findTitleUserByUserId(userId);
  }
}
