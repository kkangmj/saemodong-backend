package com.saemodong.api.mapper;

import com.saemodong.api.model.activity.contest.ActivityContestField;
import com.saemodong.api.model.activity.extra.ActivityExtraField;
import com.saemodong.api.repository.activity.ActivityContestFieldRepository;
import com.saemodong.api.repository.activity.ActivityExtraFieldRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityFieldMapper {

  private final ActivityExtraFieldRepository activityExtraFieldRepository;
  private final ActivityContestFieldRepository activityContestFieldRepository;

  public List<String> getActivityField(Long activityId, Integer activityType) {
    if (activityType.equals(0)) {
      // 대외활동
      List<ActivityExtraField> activityExtraFields =
          activityExtraFieldRepository.findAllByActivityId(activityId);
      List<String> fields =
          activityExtraFields.stream()
              .map(activityExtraField -> activityExtraField.getExtraField().getField())
              .collect(Collectors.toList());
      return fields;
    } else {
      // 공모전
      List<ActivityContestField> activityContestFields =
          activityContestFieldRepository.findAllByActivityId(activityId);
      List<String> fields =
          activityContestFields.stream()
              .map(activityContestField -> activityContestField.getContestField().getField())
              .collect(Collectors.toList());
      return fields;
    }
  }
}
