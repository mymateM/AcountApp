package com.connect.accountApp.global.config;

import com.connect.accountApp.domain.user.application.port.out.GetUserPort;
import com.connect.accountApp.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final GetUserPort findUserPort;

  @Bean
  public UserDetailsService userDetailsService() {

    return  username -> {
      User user = findUserPort.findUser(username);

      return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUserEmail())
        .password(passwordEncoder().encode(user.getUserPassword()))
        .authorities(new SimpleGrantedAuthority(user.getRole().name()))
        .build();
    };

  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    // it is responsible to fetch the userDetails and also encode password
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  // has a bunch of methods
  // method : allow us of help us to authenticate user based or using just username and password
  // has a method called authenticate, which allow us to authenticate a user based on the username and password
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    // AuthenticationConfiguration hold already the information about the authentication managers
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


}
