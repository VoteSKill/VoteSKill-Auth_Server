
package com.voteskill.user.controller;

import com.voteskill.global.jwt.JwtService;
import com.voteskill.user.entity.UserEntity;
import com.voteskill.user.service.UserService;
import com.voteskill.user.dto.UserSignUpDto;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@CrossOrigin
@Tag(name = "유저", description = "유저 관련 API 입니다.")
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;

  /**
   * Member 생성
   *
   * @return
   * @throws ParseException
   */
  @Operation(description = "유저 등록 메서드입니다.")
  @PostMapping("")
  public ResponseEntity<?> createUser(@RequestBody UserSignUpDto userSignUpDto, HttpServletRequest request) throws Exception {
    userSignUpDto.setToken(jwtService.extractAccessToken(request).orElse(null));
    String nickname = userService.signUp(userSignUpDto);

    return ResponseEntity.ok(nickname);
  }

  /**
   * User 정보 수정
   * 
   * @AuthenticationPrincipal 를 통해 인증정보를 반아오기
   *
   * @return
   * @throws ParseException
   */
//  @Operation(description = "유저 정보 수정 메서드입니다.")
//  @PutMapping()
//  public ResponseEntity<UserEntity> updateUser(@AuthenticationPrincipal UserDetails token,
//      @RequestBody UserUpdateDTO updateData) throws ParseException {
//
//    UserEntity updatedUser = userService.updateUserInfo(token, updateData);
//
//    if (!ObjectUtils.isEmpty(updatedUser)) {
//
//      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//
//    } else {
//
//      return new ResponseEntity<>(updatedUser, HttpStatus.NOT_FOUND);
//    }
//  }

  /**
   * Member List 조회
   *
   * @return
   */
  @Operation(description = "유저 전체 조회 메서드입니다.")
  @GetMapping()
  public ResponseEntity<List<UserEntity>> getUsers() {

    List<UserEntity> users = userService.getUsers();

    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  /**
   * Id에 해당하는 Member 조회
   *
   * @param id
   * @return
   */
  @Operation(description = "특정 유저 조회 메서드입니다.")
  @GetMapping("{id}")
  public ResponseEntity<UserEntity> getUser(@PathVariable("id") String id) {

    UserEntity user = userService.getUser(id);

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  /**
   * Id에 해당하는 Member 삭제
   *✨
   * @param id
   * @return
   */
  @Operation(description = "특정 유저 제거 메서드입니다.")
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {

    userService.deleteUser(id);

    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}