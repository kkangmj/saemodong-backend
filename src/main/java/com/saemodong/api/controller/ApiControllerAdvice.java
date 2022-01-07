package com.saemodong.api.controller;

import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.FailureResponse;
import com.saemodong.api.exception.BadRequestException;
import com.saemodong.api.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ApiResponse handleNotFoundException(NotFoundException e) {
    return FailureResponse.of(e.getResultCode(), e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ApiResponse handleBadRequestException(BadRequestException e) {
    return FailureResponse.of(e.getResultCode(), e.getMessage());
  }
}
