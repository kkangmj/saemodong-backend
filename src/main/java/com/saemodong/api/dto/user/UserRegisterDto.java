package com.saemodong.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterDto {
  private String nickname;
  private String apiKey;
  private String setInterest;

  public static UserRegisterDto of(String nickname, String apiKey, String setInterest) {
    return new UserRegisterDto(nickname, apiKey, setInterest);
  }
}
