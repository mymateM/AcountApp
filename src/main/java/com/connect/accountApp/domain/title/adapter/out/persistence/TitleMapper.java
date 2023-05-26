package com.connect.accountApp.domain.title.adapter.out.persistence;

import com.connect.accountApp.domain.title.adapter.out.persistence.jpa.model.TitleJpaEntity;
import com.connect.accountApp.domain.title.domain.model.Title;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {

  public Title mapToDomainEntity(TitleJpaEntity titleJpaEntity) {
    return Title.builder()
        .titleId(titleJpaEntity.getTitleId())
        .titleImg(titleJpaEntity.getTitleImg())
        .titleName(titleJpaEntity.getTitleName())
        .build();
  }

  public TitleJpaEntity mapToJpaEntity(Title title) {
    return TitleJpaEntity.builder()
        .titleId(title.getTitleId())
        .titleImg(title.getTitleImg())
        .titleName(title.getTitleName())
        .build();
  }

}
