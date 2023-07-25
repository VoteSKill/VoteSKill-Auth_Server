
 package com.voteskill.global.oauth.controller;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.voteskill.global.oauth.service.OAuthService;
 import com.voteskill.user.dto.UserOauthInfoDto;
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
 @GetMapping("")
 public ResponseEntity<?> kakaoCallback(@RequestParam String code) throws JsonProcessingException {
//  accessToken 발급받기
  String accessToken = oAuthService.getKakaoAccessToken(code);

//  userInfo 받아오기
  UserOauthInfoDto userInfo = oAuthService.getUserInfo(accessToken);
// Todo: 회원 유무 확인 및 jwt token 반환

 return ResponseEntity.ok(userInfo);
 }
 }