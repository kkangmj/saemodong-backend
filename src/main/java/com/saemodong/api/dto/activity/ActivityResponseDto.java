package com.saemodong.api.dto.activity;

import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.activity.ActivityType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityResponseDto {
  private Long id;
  private String name;
  private Integer type;
  private String url;
  private LocalDateTime openedAt;
  private LocalDateTime closedAt;
  private LocalDateTime createdAt;

  public static ActivityResponseDto of(Activity activity) {
    return new ActivityResponseDto(
        activity.getId(),
        activity.getName(),
        activity.getType(),
        activity.getUrl(),
        activity.getOpenedAt(),
        activity.getClosedAt(),
        activity.getCreatedAt());
  }
}
