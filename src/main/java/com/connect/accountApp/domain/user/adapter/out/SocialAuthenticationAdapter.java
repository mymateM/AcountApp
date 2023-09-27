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
    } else if (socialAuthType.equals("NAVER")) {
      return getNaverSocialEmail(socialAuthAccessToken);
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
    String googleAuthenticationRequest =
        "https://oauth2.googleapis.com/tokeninfo?id_token=" + socialAuthAccessToken;

    String email = "";
    String result = "";
    try {
      URL kakaoAuthenticationUrl = new URL(googleAuthenticationRequest);
      URLConnection urlConnection = kakaoAuthenticationUrl.openConnection();
      HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setDoOutput(true);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      String line = "";

      while ((line = bufferedReader.readLine()) != null) {
        result += line;
      }

      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      email += element.getAsJsonObject().get("email");

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return email;
  }

  public String getNaverSocialEmail(String socialAuthAccessToken) {
    String naverAuthenticationRequest = "https://openapi.naver.com/v1/nid/me";

    String email = "";
    String result = "";
    try {
      URL naverAuthenticationUrl = new URL(naverAuthenticationRequest);
      URLConnection urlConnection = naverAuthenticationUrl.openConnection();
      HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

      /*
       {
    "resultcode": "00",
    "message": "success",
    "response": {
        "id": "eNlpQaCgdcGIfnos-htAblT2EZ949-Qf6Ta6xWWNi9E",
        "nickname": "Europa",
        "email": "esfjge@naver.com",
        "name": "정가은"
    }
}
       */
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setRequestProperty("Authorization", "Bearer " + socialAuthAccessToken);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
      String line = "";

      while ((line = bufferedReader.readLine()) != null) {
        result += line;
      }

      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(result);

      email += element.getAsJsonObject().get("response").getAsJsonObject().get("email");
      System.out.println("email : " + email);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return email;
  }

}
