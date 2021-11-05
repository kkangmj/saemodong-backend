package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public class FailureResponse extends ApiResponse {

  private Integer code;
  private String message;

  protected FailureResponse(ResultCode resultCode, Integer code, String message) {
    super(resultCode);
    this.code = code;
    this.message = message;
  }

  public static FailureResponse of(ResultCode resultCode) {
    return new FailureResponse(resultCode, resultCode.getCode(), resultCode.getDefaultMessage());
  }

  public static FailureResponse of(ResultCode resultCode, String message) {
    return new FailureResponse(resultCode, resultCode.getCode(), message);
  }
}
