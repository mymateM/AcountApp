package com.connect.accountApp.domain.user.adapter.out;

import com.connect.accountApp.domain.user.application.port.out.GetUserSocialEmailPort;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialAuthenticationAdapter implements GetUserSocialEmailPort {

  @Override
  public String getUserSocialEmail(String socialAuthType, String socialAuthAccessToken) {

    System.out.println("socialAuthType = " + socialAuthType);
    if (socialAuthType.equals("KAKAO"))
      return getKakaoSocialEmail(socialAuthAccessToken);
    else if (socialAuthType.equals("GOOGLE")) {
      return getGoogleSocialEmail(socialAuthAccessToken);
    }

    return null;
  }

  public String getKakaoSocialEmail(String socialAuthAccessToken) {
    String kakaoAuthenticationRequest = "https://kapi.kakao.com/v2/user/me";

    String id = "";
    String result = "";
    try {
      URL kakaoAuthenticationUrl = new URL(kakaoAuthenticationRequest);
      URLConnection urlConnection = kakaoAuthenticationUrl.openConnection();
      HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + socialAuthAccessToken);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      String line = "";

      while ((line = bufferedReader.readLine()) != null) {
        result += line;
      }

      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      id += element.getAsJsonObject().get("id");
      System.out.println("id : " + id);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return id + "@kakao";
  }


  public String getGoogleSocialEmail(String socialAuthAccessToken) {
    String kakaoAuthenticationRequest = "https://kapi.kakao.com/v2/user/me";

    String id = "";
    String result = "";
    try {
      URL kakaoAuthenticationUrl = new URL(kakaoAuthenticationRequest);
      URLConnection urlConnection = kakaoAuthenticationUrl.openConnection();
      HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + socialAuthAccessToken);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      String line = "";

      while ((line = bufferedReader.readLine()) != null) {
        result += line;
      }

      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      id += element.getAsJsonObject().get("id");
      System.out.println("id : " + id);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return id + "@kakao";
  }

}
