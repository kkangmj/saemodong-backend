package com.saemodong.api.dto;

public abstract class ApiResponse {

  private final ResultCode resultCode;

  protected ApiResponse(ResultCode resultCode) {
    this.resultCode = resultCode;
  }
}
