package com.connect.accountApp.domain.user.application.port.in;

public interface UserPasswordValidateUseCase {

  Boolean validatePassword(String userEmail, String password);

}
