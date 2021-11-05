package com.saemodong.api.dto;

import lombok.Getter;

@Getter
public class SuccessResponse<T> extends ApiResponse {

  private T result;

  private SuccessResponse(T result) {
    super(ResultCode.OK);
    this.result = result;
  }

  public static <T> SuccessResponse<T> of(T result) {
    return new SuccessResponse(result);
  }
}
