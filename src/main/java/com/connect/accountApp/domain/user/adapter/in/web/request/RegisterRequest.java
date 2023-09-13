package com.connect.accountApp.domain.user.adapter.in.web.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


  private String nickname;

  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private String email;

  private String password;

}
