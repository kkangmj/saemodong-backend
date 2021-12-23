package com.saemodong.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponseDto {

  private String nickname;
  private String setInterest;

  public static UserLoginResponseDto of(String nickname, String setInterest) {
    return new UserLoginResponseDto(nickname, setInterest);
  }
}
