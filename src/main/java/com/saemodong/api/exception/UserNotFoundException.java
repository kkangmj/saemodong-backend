package com.saemodong.api.exception;

import com.saemodong.api.dto.ResultCode;

public class UserNotFoundException extends NotFoundException {

  public UserNotFoundException(String apiKey) {
    super(ResultCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다. apiKey:" + apiKey);
  }
}
