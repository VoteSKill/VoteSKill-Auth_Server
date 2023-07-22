
 package com.ssacation.ssacation.global.oauth;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import lombok.AllArgsConstructor;

 @RestController
 @AllArgsConstructor
 @RequestMapping("/oauth")
 public class OAuthController {

 private final OAuthService oAuthService;

 /**
 * 카카오 callback
 * [GET] /oauth/kakao/callback
 */
 @CrossOrigin
 @ResponseBody
 @GetMapping("/kakao")
 public ResponseEntity<?> kakaoCallback(@RequestParam String code) throws JsonProcessingException {
//  accessToken 발급받기
  String accessToken = oAuthService.getKakaoAccessToken(code);
  System.out.println("zzz");

//  userInfo 받아오기
  Object userInfo = oAuthService.getUserInfo(accessToken);

 return ResponseEntity.ok("성공~!");
 }
 }