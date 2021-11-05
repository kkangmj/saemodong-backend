package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public enum ResultCode {
  // SUCCESS CODE
  OK(200000, "요청이 정상적으로 처리되었습니다."),

  // ERROR CODE
  NOT_FOUND(404000, "요청한 자원이 존재하지 않습니다.");

  private final Integer code;
  private final String defaultMessage;

  ResultCode(Integer code, String defaultMessage) {
    this.code = code;
    this.defaultMessage = defaultMessage;
  }
}
