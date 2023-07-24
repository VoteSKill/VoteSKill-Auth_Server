package com.voteskill.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class UserOauthInfoDto {
    String social_id;
    String nickname;
    boolean isUser;

}
