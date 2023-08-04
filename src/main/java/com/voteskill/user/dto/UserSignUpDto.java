
package com.voteskill.user.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDto {
    private String socialId;
    private String email;
    private String nickname;
    private int age;
    private String token;
}