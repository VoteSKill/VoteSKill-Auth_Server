package com.voteskill.global.oauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class OAuthServiceTest {

//    @InjectMocks
//    private OAuthService oAuthService;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String clientId;
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    private String secretKey;
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    private String redirectUri;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    public void testGetKakaoAccessToken() throws Exception {
//        // Arrange
//        String mockCode = "mock_code";
//        String mockAccessToken = "mock_access_token";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", clientId);
//        params.add("redirect_uri", redirectUri);
//        params.add("code", mockCode);
//        params.add("client_secret", secretKey);
//
//        // 요청 헤더와 파라미터를 HttpEntity에 설정
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//
//        // Kakao API에서 반환할 응답
//        ResponseEntity<String> responseEntity = ResponseEntity.ok()
//                .body("{\"access_token\":\"" + mockAccessToken + "\"}");
//
//        // restTemplate Mock 설정
//        when(restTemplate.exchange(eq("https://kauth.kakao.com/oauth/token"), eq(HttpMethod.POST), eq(requestEntity), eq(String.class)))
//                .thenReturn(responseEntity);
//
//        // Act
//        String accessToken = oAuthService.getKakaoAccessToken(mockCode);
//        // Assert
//        assertEquals(mockAccessToken, accessToken);
//    }
//
//    @Test
//    public void testGetUserInfo() throws Exception {
//        // Arrange
//        String mockAccessToken = "mock_access_token";
//        String mockResponse = "{\"properties\":{\"nickname\":\"John Doe\",\"id\":\"123456\"}}";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(mockAccessToken);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        ResponseEntity<String> responseEntity = ResponseEntity.ok()
//                .headers(headers)
//                .body(mockResponse);
//
//        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
//                .thenReturn(responseEntity);
//
//        // Act
//        Object userInfo = oAuthService.getUserInfo(mockAccessToken);
//
//        // Assert
//        HashMap<String, Object> expectedUserInfo = new HashMap<>();
//        expectedUserInfo.put("nickname", "John Doe");
//        assertEquals(expectedUserInfo, userInfo);
//    }
}
