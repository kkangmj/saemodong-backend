package com.saemodong.api.service.activity;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import java.util.List;
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
      Integer currentPage, Integer totalPage, List<ActivityResponseDto> result) {

    return new ActivityPageResponse(currentPage, totalPage, result);
  }
}
