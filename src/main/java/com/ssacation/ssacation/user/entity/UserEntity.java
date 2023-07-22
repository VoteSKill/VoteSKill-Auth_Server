package com.ssacation.ssacation.user.entity;

import com.ssacation.ssacation.user.common.Role;
import com.ssacation.ssacation.user.common.SocialType;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.stream.Stream;


import javax.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User") // 테이블과 클래스명이 같을 경우 생략 가능
@Entity
public class UserEntity {

  // @GeneratedValue(strategy = GenerationType.xxx) : Primary Key의 키 생성
  // 전략(Strategy)을 설정하고자 할 때 사용
  // GenerationType.IDENTITY : MySQL의 AUTO_INCREMENT 방식을 이용
  // GenerationType.AUTO(default) : JPA 구현체(Hibernate)가 생성 방식을 결정
  // GenerationType.SEQUENCE : DB의 SEQUENCE를 이용해서 키를 생성. @SequenceGenerator와 같이 사용
  // GenerationType.TABLE : 키 생성 전용 테이블을 생성해서 키 생성. @TableGenerator와 함께 사용

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  // Timestamp의 값을 현재 시간으로 자동 설정
  @Column(length = 200, nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp regDate;

  private String email;
  private String password;
  private Date birthDay;
  private String nickname;
  private String imageUrl;
  private String phoneNum;
  private String refreshToken;
  private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

  @Enumerated(EnumType.STRING)
  private SocialType socialType; // KAKAO, NAVER, GOOGLE

  @Enumerated(EnumType.STRING)
  private Role role;

  // 유저 권한 승격 메소드
  public void authorizeUser() {
    this.role = Role.USER;
  }

  public boolean isUserInfoEmpty() {

    return Stream.of(birthDay, phoneNum)
        .anyMatch(Objects::isNull);
  }

  // 비밀번호 암호화 메소드
  public void passwordEncode(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
  }

  public void updateRefreshToken(String updateRefreshToken) {
    this.refreshToken = updateRefreshToken;
  }
}