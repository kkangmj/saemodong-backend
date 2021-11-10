package com.saemodong.api.mapper;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.model.BookmarkType;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.repository.BookmarkRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

  private final ActivityFieldMapper activityFieldMapper;
  private final BookmarkRepository bookmarkRepository;

  public List<ActivityResponseDto> toActivityResponseDto(
      List<Activity> activityList, String apiKey) {

    return activityList.stream()
        .map(
            activity ->
                ActivityResponseDto.of(
                    activity,
                    activityFieldMapper.getActivityField(activity.getId(), activity.getType()),
                    bookmarkRepository
                        .findByTypeAndContentIdAndUserApiKey(
                            BookmarkType.ACTIVITY.getType(), activity.getId(), apiKey)
                        .isPresent()))
        .collect(Collectors.toList());
  }
}
