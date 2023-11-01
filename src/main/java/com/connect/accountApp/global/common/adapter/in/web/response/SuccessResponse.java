package com.connect.accountApp.global.common.adapter.in.web.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponse<T> {

  public static final String SUCCESS_MESSAGE = "Ok";
  public static final String CREATED_MESSAGE = "Created";
  public static final String NO_CONTENT_MESSAGE = "No Content";

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
    return new SuccessResponse(CREATED_MESSAGE, 201, data);
  }

  public static <T> SuccessResponse create204NoContentResponse() {
    return new SuccessResponse(NO_CONTENT_MESSAGE, 204, null);
  }
}
