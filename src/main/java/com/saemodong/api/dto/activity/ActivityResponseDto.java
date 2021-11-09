package com.saemodong.api.dto.activity;

import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.activity.ActivityType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityResponseDto {
  private Long id;
  private String name;
  private Integer type;
  private List<String> field;
  private String url;
  private LocalDateTime openedAt;
  private LocalDateTime closedAt;
  private LocalDateTime createdAt;

  public static ActivityResponseDto of(Activity activity, List<String> activityField) {
    return new ActivityResponseDto(
        activity.getId(),
        activity.getName(),
        activity.getType(),
        activityField,
        activity.getUrl(),
        activity.getOpenedAt(),
        activity.getClosedAt(),
        activity.getCreatedAt());
  }
}
