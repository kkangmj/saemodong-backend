package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public class SuccessResponse<T> extends BaseResponse {

  private T result;

  protected SuccessResponse(Integer resultCode, String message) {
    super(resultCode, message);
  }

  private SuccessResponse(Integer resultCode, String message, T result) {
    super(resultCode, message);
    this.result = result;
  }

  public static <T> SuccessResponse<T> of(ResultCode resultCode, T result) {
    return new SuccessResponse(resultCode.getCode(), resultCode.getDefaultMessage(), result);
  }
}
