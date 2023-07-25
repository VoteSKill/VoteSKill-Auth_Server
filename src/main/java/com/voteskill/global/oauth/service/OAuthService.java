// WebClient를 활용한 SNS 로그인 서비스
package com.voteskill.global.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voteskill.user.dto.UserOauthInfoDto;
import com.voteskill.user.entity.UserEntity;
import com.voteskill.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
//@AllArgsConstructor
public class OAuthService {

//  private final UserRepository userRepository;

  @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
  private String clientId;
  @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
  private String secretKey;
  @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
  private String redirectUri;

  // 카카오 서버로 부터 Access 토큰값 받아오기
  public String getKakaoAccessToken(String code) throws JsonProcessingException {

    String kakaoAuthUrl = "https://kauth.kakao.com/oauth/token";

// WebClient kakaoWebClient = WebClient.builder()
// .baseUrl(kakaoAuthUrl)
// .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
// .build();
//
// // 카카오 Auth API 호출
// @SuppressWarnings("unchecked")
// Map<String, Object> tokenResponse = kakaoWebClient.post()
// .uri(uriBuilder -> uriBuilder
// .path("/oauth/token")
// .queryParam("grant_type", "authorization_code")
// .queryParam("client_id", clientId)
// .queryParam("code", code)
// .build())
// .retrieve()
// .bodyToMono(Map.class)
// .block();

// String accessToken = (String) tokenResponse.get("access_token");
// log.info("accessToken : " + accessToken);
// return accessToken;
    // POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
    // 이 때 필요한 라이브러리가 RestTemplate, 얘를 쓰면 http 요청을 편하게 할 수 있다.
    RestTemplate rt = new RestTemplate();

    // HTTP POST를 요청할 때 보내는 데이터(body)를 설명해주는 헤더도 만들어 같이 보내줘야 한다.
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded");

    // body 데이터를 담을 오브젝트인 MultiValueMap를 만들어보자
    // body는 보통 key, value의 쌍으로 이루어지기 때문에 자바에서 제공해주는 MultiValueMap 타입을 사용한다.
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", clientId);
    params.add("redirect_uri", redirectUri);
    params.add("code", code);
    params.add("client_secret", secretKey);

    // 요청하기 위해 헤더(Header)와 데이터(Body)를 합친다.
    // kakaoTokenRequest는 데이터(Body)와 헤더(Header)를 Entity가 된다.
    HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

    // POST 방식으로 Http 요청한다. 그리고 response 변수의 응답 받는다.
    ResponseEntity<String> response = rt.exchange(
        "https://kauth.kakao.com/oauth/token", // https://{요청할 서버 주소}
        HttpMethod.POST, // 요청할 방식
        kakaoTokenRequest, // 요청할 때 보낼 데이터
        String.class // 요청 시 반환되는 데이터 타입
    );
    String responseBody = response.getBody();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(responseBody);
    String accessToken = jsonNode
        .get("access_token").asText();
    return accessToken;
  }

  // 액세스 토큰으로 카카오 서버에서 유저 정보 받아오기
  public UserOauthInfoDto getUserInfo(String accessToken) throws JsonProcessingException {

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    System.out.println(accessToken);
    // HTTP 요청 보내기
    HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
    RestTemplate rt = new RestTemplate();
    ResponseEntity<String> response = rt.exchange(
        "https://kapi.kakao.com/v2/user/me",
        HttpMethod.POST,
        kakaoUserInfoRequest,
        String.class
    );

    String responseBody = response.getBody();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(responseBody);
    String nickname = jsonNode.get("properties")
        .get("nickname").asText();
    String socialId = jsonNode.get("id").asText();
    HashMap<String,Object> responseMap = new HashMap<>();
    responseMap.put("nickname",nickname);
    responseMap.put("socialId",socialId);

//    Optional<UserEntity> user = userRepository.findById(socialId);
//    user.ifPresent()
//    if (checkUser(Long.parseLong(socialId))) {
//      userOauthInfoDto = UserOauthInfoDto.builder()
//          .isUser(checkUser(Long.parseLong(socialId)))
//          .nickname(userR)
//          .build();
//    } else {
//      userOauthInfoDto = UserOauthInfoDto.builder()
//          .isUser(checkUser(Long.parseLong(socialId))).build();
//    }



    UserOauthInfoDto userInfo = UserOauthInfoDto.builder()
        .social_id("1")
        .nickname("1")
        .build();

    return userInfo;
  }

//  private boolean checkUser(Long socialId) {
//    return userRepository.findById(socialId).isPresent();
//  }
}