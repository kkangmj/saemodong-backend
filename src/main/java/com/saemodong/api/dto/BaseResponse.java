package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public abstract class BaseResponse {

  private final Integer resultCode;
  private final String message;

  protected BaseResponse(Integer resultCode, String message) {
    this.resultCode = resultCode;
    this.message = message;
  }
}
