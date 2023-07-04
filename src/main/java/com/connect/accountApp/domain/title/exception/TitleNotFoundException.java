package com.connect.accountApp.domain.title.exception;

import com.connect.accountApp.global.error.ErrorCode;
import com.connect.accountApp.global.error.exception.BusinessException;

public class TitleNotFoundException extends BusinessException {

  public TitleNotFoundException(String message) {
    super(message, ErrorCode.TITLE_NOT_FOUND);
  }
}
