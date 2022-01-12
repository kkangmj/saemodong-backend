package com.saemodong.api.dto.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestActivityDto {
  private String lastVisitedAt;

  public static InterestActivityDto of(String lastVisitedAt) {
    return new InterestActivityDto(lastVisitedAt);
  }
}
