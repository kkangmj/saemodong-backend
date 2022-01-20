package com.saemodong.api.exception;

import com.saemodong.api.dto.ResultCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

  private ResultCode resultCode;

  public BadRequestException() {
    super(ResultCode.BAD_REQUEST.getDefaultMessage());
    this.resultCode = ResultCode.BAD_REQUEST;
  }

  public BadRequestException(String message) {
    super(message);
    this.resultCode = ResultCode.BAD_REQUEST;
  }

  public BadRequestException(ResultCode resultCode, String message) {
    super(message);
    this.resultCode = resultCode;
  }
}
