package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.UserLoginDto;
import com.saemodong.api.dto.user.UserRegisterResponseDto;

public interface UserService {
  UserRegisterResponseDto registerByNickname(String nickname);

  UserLoginDto loginByApiKey(String apiKey);
}
