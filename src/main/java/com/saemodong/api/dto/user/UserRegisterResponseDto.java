package com.saemodong.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterResponseDto {
  private String nickname;
  private String apiKey;
  private String feedbackUrl;

  public static UserRegisterResponseDto of(String nickname, String apiKey) {
    return new UserRegisterResponseDto(nickname, apiKey, "");
  }
}
