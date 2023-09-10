package com.connect.accountApp.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FcmConfig {

  @Value("${spring.application.fcm.admin-sdk}")
  public String adminSdk;

  @Bean
  FirebaseMessaging firebaseMessaging() throws IOException {
    FileInputStream refreshToken = new FileInputStream(adminSdk);
    FirebaseApp firebaseApp = null;

    List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

    // 이미 init 된 경우 기존의 app을 사용하도록 함
    if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
      for (FirebaseApp app : firebaseAppList) {
        if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
          firebaseApp = app;
        }
      }
    } else {
      FirebaseOptions options = FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(refreshToken))
          .build();

      firebaseApp = FirebaseApp.initializeApp(options);
    }

    return FirebaseMessaging.getInstance(firebaseApp);
  }
}
