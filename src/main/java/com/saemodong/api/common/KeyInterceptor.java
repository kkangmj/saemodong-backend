package com.saemodong.api.common;

import com.saemodong.api.exception.BadRequestException;
import com.saemodong.api.exception.UserNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class KeyInterceptor implements HandlerInterceptor {

  private final KeyHelper keyHelper;

  @Override
  public boolean preHandle(
      HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {

    String apiKey = request.getParameter("apiKey");

    if (apiKey.isEmpty()) {
      throw new BadRequestException();
    }
    if (keyHelper.validate(apiKey)) {
      return true;
    } else {
      throw new UserNotFoundException(apiKey);
    }
  }
}
