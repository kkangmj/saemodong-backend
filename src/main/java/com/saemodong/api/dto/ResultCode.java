package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public enum ResultCode {
  OK(200000, "정상적으로 처리되었습니다."),

  BAD_REQUEST(400000, "잘못된 요청입니다."),
  NICKNAME_DUPLICATE(400001, "이미 사용중인 닉네임입니다."),
  NOT_FOUND(404000, "요청한 자원이 존재하지 않습니다."),
  USER_NOT_FOUND(404001, "사용자를 찾을 수 없습니다.");

  private final Integer code;
  private final String defaultMessage;

  ResultCode(Integer code, String defaultMessage) {
    this.code = code;
    this.defaultMessage = defaultMessage;
  }
}
