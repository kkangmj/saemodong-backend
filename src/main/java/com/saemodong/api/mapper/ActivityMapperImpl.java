package com.saemodong.api.mapper;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.exception.BadRequestException;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.activity.contest.ActivityContestField;
import com.saemodong.api.model.activity.extra.ActivityExtraField;
import com.saemodong.api.model.bookmark.BookmarkType;
import com.saemodong.api.repository.activity.ActivityContestFieldRepository;
import com.saemodong.api.repository.activity.ActivityExtraFieldRepository;
import com.saemodong.api.repository.bookmark.BookmarkRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("activityMapper")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityMapperImpl implements ActivityMapper {

  private final BookmarkRepository bookmarkRepository;
  private final ActivityExtraFieldRepository activityExtraFieldRepository;
  private final ActivityContestFieldRepository activityContestFieldRepository;

  @Override
  public List<ActivityResponseDto> toActivityResponseDto(
      List<Activity> activityList, String apiKey) {

    return activityList.stream()
        .map(
            activity ->
                ActivityResponseDto.of(
                    activity,
                    getNameOfActivityField(activity.getId(), activity.getType()),
                    isBookmarkedByUser(activity.getId(), apiKey)))
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getNameOfActivityField(Long activityId, Integer activityType) {

    switch (activityType) {
      case 0:
        List<ActivityExtraField> activityExtraFields =
            activityExtraFieldRepository.findAllByActivityId(activityId);
        return activityExtraFields.stream()
            .map(activityExtraField -> activityExtraField.getExtraField().getField())
            .collect(Collectors.toList());

      case 1:
        List<ActivityContestField> activityContestFields =
            activityContestFieldRepository.findAllByActivityId(activityId);
        return activityContestFields.stream()
            .map(activityContestField -> activityContestField.getContestField().getField())
            .collect(Collectors.toList());

      default:
        throw new BadRequestException("유효하지 않은 타입입니다.");
    }
  }

  private boolean isBookmarkedByUser(Long activityId, String apiKey) {
    return bookmarkRepository
        .findByTypeAndContentIdAndUserApiKey(BookmarkType.ACTIVITY.getType(), activityId, apiKey)
        .isPresent();
  }
}
