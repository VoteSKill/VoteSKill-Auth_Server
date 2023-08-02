
package com.voteskill.user.service;

import com.voteskill.user.common.Role;
import com.voteskill.user.entity.UserEntity;
import com.voteskill.user.dto.UserSignUpDto;
import com.voteskill.user.repository.UserRepository;
import java.util.List;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * User 생성
   * JPA Repository의 save Method를 사용하여 객체를 생성
   * Entity인 Model 객체에 @Id로 설정한 키 값이 없을 경우 해당하는 데이터를 추가
   * 만약 추가하려는 Entity인 Model 객체에 @Id 값이 이미 존재하면 갱신되기 때문에
   * 아래와 같이 추가하고자 하는 User가 존재하는지 체크하는 로직을 추가
   * 
   * @param model
   * @return
   */
  public void signUp(UserSignUpDto userSignUpDto) throws Exception {

    log.info("회원가입 시도 : {} {}",userSignUpDto.getSocialId(), userSignUpDto.getNickname());

    if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
      throw new Exception("이미 존재하는 닉네임입니다.");
    }

    UserEntity user = UserEntity.builder()
        .socialId(userSignUpDto.getSocialId())
        .email(userSignUpDto.getEmail())
        .nickname(userSignUpDto.getNickname())
        .role(Role.USER)
        .build();

    log.info("user socialId : {}",user.getSocialId());

    userRepository.save(user);
  }


  /**
   * User List 조회
   * JPA Repository의 findAll Method를 사용하여 전체 User를 조회
   * 
   * @return
   */
  public List<UserEntity> getUsers() {
    return userRepository.findAll();
  }

  /**
   * Id에 해당하는 User 조회
   * JPA Repository의 findBy Method를 사용하여 특정 User를 조회
   * find 메소드는 NULL 값일 수도 있으므로 Optional<T>를 반환하지만,
   * Optional 객체의 get() 메소드를 통해 Entity로 변환해서 반환함.
   *
   * @param id
   * @return
   */
  public UserEntity getUser(String id) {
    return userRepository.findBySocialId(id).get();
  }

  public Optional<UserEntity> getUserByNickname(String nickname){
    return userRepository.findByNickname(nickname);
  }

  /**
   * Id에 해당하는 User 삭제
   * JPA Repository의 deleteBy Method를 사용하여 특정 User를 삭제
   * 
   * @param id
   */
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}