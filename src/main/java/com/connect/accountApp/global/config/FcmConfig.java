package com.connect.accountApp.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FcmConfig {

  @Bean
  FirebaseMessaging firebaseMessaging() throws IOException {
    // ec2 배포시 FileInputStream은 기존의 절대 경로를 사용하기 때문에 문제 발생 -> ClassPathResource 사용
    ClassPathResource resource = new ClassPathResource(
        "firebase/fcm-mymate-firebase-adminsdk-zjwbt-aa9e97d875.json");

    InputStream refreshToken = resource.getInputStream();

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
