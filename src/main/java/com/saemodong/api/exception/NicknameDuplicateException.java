package com.saemodong.api.exception;

import com.saemodong.api.dto.ResultCode;

public class NicknameDuplicateException extends BadRequestException {

  public NicknameDuplicateException(String nickname) {
    super(ResultCode.NICKNAME_DUPLICATE, "이미 사용중인 닉네임입니다. 닉네임: " + nickname);
  }
}
