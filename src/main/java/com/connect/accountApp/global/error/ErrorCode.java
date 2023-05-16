package com.connect.accountApp.global.error;


import lombok.Getter;

@Getter
public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE(400, "C001", "올바르지 않은 입력값 입니다."),
  METHOD_NOT_ALLOWED(405, "C002", "올바르지 않은 함수 호출입니다."),
  ENTITY_NOT_FOUND(404, "C003", "요청한 값이 존재하지 않습니다."),
  INTERNAL_SERVER_ERROR(500, "C004", "서버 에러"),
  INVALID_TYPE_VALUE(400, "C005", "올바르지 않은 타입입니다."),
  INVALID_REQUEST(400, "C010", "잘못된 요청입니다.");


  private int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
