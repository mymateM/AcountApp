package com.connect.accountApp.global.error;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private String type;
  private String title;
  private int status;
  private String detail;
  private String instance;

  private int code;
  private List<FieldError> errors;

  private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
    this.title = code.getTitle();
    this.status = code.getStatus();
    this.detail = code.getDetail();
    this.code = code.getCode();
    this.errors = errors;
  }

  private ErrorResponse(final ErrorCode code) {
    this.title = code.getTitle();
    this.status = code.getStatus();
    this.detail = code.getDetail();
    this.code = code.getCode();
    this.errors = new ArrayList<>();
  }

  public static ErrorResponse of(final ErrorCode code) {
    return new ErrorResponse(code);
  }

  public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
    return new ErrorResponse(code, errors);
  }

  public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
    return new ErrorResponse(code, FieldError.of(bindingResult));
  }



  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED) // ConstraintValidatorContext 에서 생성한 값 저장
  public static class FieldError {

    private String field;
    private String value;
    private String reason;

    public FieldError(String field, String value, String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    public static List<FieldError> of(final String field, final String value, final String reason) {
      List<FieldError> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldError(field, value, reason));
      return fieldErrors;
    }

    public static List<FieldError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error -> new FieldError(
              error.getField(),
              error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
              error.getDefaultMessage()))
          .collect(Collectors.toList());
    }

  }

}
