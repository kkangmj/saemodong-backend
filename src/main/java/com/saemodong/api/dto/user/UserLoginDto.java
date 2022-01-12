package com.saemodong.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginDto {

  private String nickname;
  private String setInterest;

  public static UserLoginDto of(String nickname, String setInterest) {
    return new UserLoginDto(nickname, setInterest);
  }
}
