package com.saemodong.api.config;

import com.saemodong.api.common.KeyHelper;
import com.saemodong.api.common.KeyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final KeyHelper keyHelper;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    String[] path = {"/api/v1/activity/**", "/api/v1/bookmark/**", "/api/v1/notification/**"};

    registry.addInterceptor(new KeyInterceptor(keyHelper)).addPathPatterns(path);
  }
}
