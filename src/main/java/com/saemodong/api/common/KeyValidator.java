package com.saemodong.api.common;

import com.saemodong.api.model.user.User;
import com.saemodong.api.repository.user.UserRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyValidator {

  private  final UserRepository userRepository;

  public boolean validate(String apiKey){
    Optional<User> user = userRepository.findByApiKey(apiKey);
    return user.isPresent();
  }

}
