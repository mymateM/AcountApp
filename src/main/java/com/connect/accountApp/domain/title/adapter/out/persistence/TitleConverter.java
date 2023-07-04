package com.connect.accountApp.domain.title.adapter.out.persistence;

import com.connect.accountApp.domain.title.domain.model.Title;
import com.connect.accountApp.domain.title.exception.TitleNotFoundException;
import com.connect.accountApp.global.error.exception.BusinessException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class TitleConverter implements AttributeConverter<Title, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Title attribute) {
    if (attribute == null) return null;
    return attribute.getTitleCode();
  }

  @Override
  public Title convertToEntityAttribute(Integer dbData) {
    if (dbData == null) return null;
    return Arrays.stream(Title.values())
        .filter(title -> title.getTitleCode().equals(dbData))
        .findFirst().orElseThrow(()->new TitleNotFoundException(dbData + "가 존재하지 않습니다."));

  }
}
