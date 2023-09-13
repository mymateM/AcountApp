package com.connect.accountApp.global.common.adapter.in.web.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponse<T> {

  public static final String SUCCESS_MESSAGE = "Success";
  public static final String CREATED_MESSAGE = "Created";

  private String message;
  private int status;
  private T data;


  public static <T> SuccessResponse create200SuccessResponse(T data) {
    return new SuccessResponse(SUCCESS_MESSAGE, 200, data);
  }

  public static <T> SuccessResponse create200SuccessResponse() {
    return new SuccessResponse(SUCCESS_MESSAGE, 200, null);
  }

  public static <T> SuccessResponse create201CreatedResponse(T data) {
    return new SuccessResponse(CREATED_MESSAGE, 200, data);
  }
}
