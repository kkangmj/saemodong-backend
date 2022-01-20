package com.saemodong.api.service.user;

import com.saemodong.api.common.KeyHelper;
import com.saemodong.api.dto.user.UserLoginDto;
import com.saemodong.api.dto.user.UserRegisterDto;
import com.saemodong.api.exception.NicknameDuplicateException;
import com.saemodong.api.exception.UserNotFoundException;
import com.saemodong.api.model.user.User;
import com.saemodong.api.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final KeyHelper keyHelper;

  public UserRegisterDto registerByNickname(String nickname) {

    if (userRepository.existsByNickname(nickname)) {
      throw new NicknameDuplicateException(nickname);
    }

    String apiKey = keyHelper.getApiKey();
    userRepository.save(User.of(nickname, apiKey));

    return UserRegisterDto.of(nickname, apiKey, "N");
  }

  public UserLoginDto loginByApiKey(String apiKey) {

    User user =
        userRepository.findByApiKey(apiKey).orElseThrow(() -> new UserNotFoundException(apiKey));

    return UserLoginDto.of(user.getNickname(), user.getSetInterest());
  }
}
