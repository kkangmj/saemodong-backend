package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.UserLoginDto;
import com.saemodong.api.dto.user.UserRegisterDto;

public interface UserService {
  UserRegisterDto registerByNickname(String nickname);

  UserLoginDto loginByApiKey(String apiKey);
}
