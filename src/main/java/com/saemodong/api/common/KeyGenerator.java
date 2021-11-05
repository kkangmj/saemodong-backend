package com.saemodong.api.common;

import com.saemodong.api.repository.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyGenerator {

  private final UserRepository userRepository;

  public String getApiKey() {
    String apiKey = generateApiKey(15);
    while (checkIfExist(apiKey)) {
      apiKey = generateApiKey(15);
    }
    return apiKey;
  }

  private boolean checkIfExist(String apiKey) {
    return userRepository.existsByApiKey(apiKey);
  }

  private String generateApiKey(Integer length) {
    char[] tmp = new char[length];
    for (int i = 0; i < tmp.length; i++) {
      int div = (int) Math.floor(Math.random() * 2);

      if (div == 0) {
        tmp[i] = (char) (Math.random() * 10 + '0');
      } else {
        tmp[i] = (char) (Math.random() * 26 + 'A');
      }
    }
    return new String(tmp);
  }
}
