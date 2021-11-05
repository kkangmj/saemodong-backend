package com.saemodong.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "세모동 API 명세서"))
@Configuration
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi activityApis() {
    return GroupedOpenApi.builder().group("activity").pathsToMatch("/**/activity/**").build();
  }

  @Bean
  public GroupedOpenApi userApis() {
    return GroupedOpenApi.builder().group("user").pathsToMatch("/**/user/**").build();
  }
}
