package org.example.service.oauth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoOauthService {
    private String REST_API_KEY = "fe2ce67ae1aa8d5ab53a015eb2a03bea";
    private String REDIRECT_URI = "http://localhost:8080/kakao/login";

    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + REST_API_KEY);  // REST_API_KEY 입력
            sb.append("&redirect_uri=" + REDIRECT_URI);  // 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public JsonObject getUserInfo(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result.toString());
            JsonObject response = element.getAsJsonObject();

            JsonObject userInfo = new JsonObject();

            // ID 추출
            long id = response.get("id").getAsLong();
            userInfo.addProperty("id", id);

            // 닉네임 추출
            JsonObject properties = response.getAsJsonObject("properties");
            if (properties != null && properties.has("nickname")) {
                String nickname = properties.get("nickname").getAsString();
                userInfo.addProperty("nickname", nickname);
            }

            // 카카오 계정 정보 추출
            JsonObject kakaoAccount = response.getAsJsonObject("kakao_account");
            if (kakaoAccount != null) {
                if (kakaoAccount.has("email")) {
                    String email = kakaoAccount.get("email").getAsString();
                    userInfo.addProperty("email", email);
                }

                JsonObject profile = kakaoAccount.getAsJsonObject("profile");
                if (profile != null && profile.has("nickname")) {
                    String profileNickname = profile.get("nickname").getAsString();
                    userInfo.addProperty("profileNickname", profileNickname);
                }
            }

            return userInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}