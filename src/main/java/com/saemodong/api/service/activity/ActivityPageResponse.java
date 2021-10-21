package com.saemodong.api.service.activity;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.model.activity.Activity;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ActivityPageResponse {
  private Integer currentPage;
  private Integer totalPage;
  private List<ActivityResponseDto> pageResult;

  public static ActivityPageResponse toPageResponse(
      Integer currentPage, Integer totalPage, List<Activity> pageResult) {
    List<ActivityResponseDto> result =
        pageResult.stream().map(ActivityResponseDto::of).collect(Collectors.toList());
    return new ActivityPageResponse(currentPage , totalPage, result);
  }
}
