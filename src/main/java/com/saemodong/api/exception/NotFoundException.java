package com.saemodong.api.exception;

import com.saemodong.api.dto.ResultCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

  private ResultCode resultCode;

  public NotFoundException(ResultCode resultCode, String message) {
    super(message);
    this.resultCode = resultCode;
  }
}
