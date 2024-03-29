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
  INVALID_REQUEST(400, "C010", "잘못된 요청입니다."),

  // USER
  USER_NOT_FOUND(404, "U001", "존재하지 않는 사용자입니다."),
  USER_EMAIL_DUPLICATED(400, "U002", "이미 존재하는 사용자 이메일입니다."),
  USER_EXISTED_HOUSEHOLD_MEMBER(400, "U002", "이미 존재하는 가구원 입니다."),

  // Household
  HOUSEHOLD_NOT_FOUND(404, "H001", "존재하지 않는 가구입니다."),

  // Title
  TITLE_NOT_FOUND(404, "T001", "존재하지 않는 타이틀입니다."),

  // ActivityNotification
  ACTIVITY_NOTIFICATION_NOT_FOUND(404, "AN001", "존재하지 않는 활동 알림입니다."),

  // Expense
  EXPENSE_NOT_FOUND(404, "E001", "존재하지 않는 지출입니다."),
  EXPENSE_DELEGATE_USER_NOT_FOUND(400, "E002", "실제 지출한 가구원을 입력하세요."),

  // bill
  BILL_NOT_FOUND(404, "B001", "존재하지 않는 고지서 아이디 입니다."),

  // global
  DATE_NOT_FOUND(400, "D001", "정확한 날짜를 입력해주세요.");

  private int status;
  private final String code;
  private final String message;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
