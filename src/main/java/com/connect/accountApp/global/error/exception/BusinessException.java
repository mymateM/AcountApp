package com.connect.accountApp.global.error.exception;

import com.swulab.eatswunee.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private ErrorCode errorCode;

  public BusinessException(final ErrorCode errorCode, final String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusinessException(final ErrorCode errorCode) {
    super(errorCode.getTitle());
    this.errorCode = errorCode;
  }



}
