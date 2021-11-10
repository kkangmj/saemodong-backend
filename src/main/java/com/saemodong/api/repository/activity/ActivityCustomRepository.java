package com.saemodong.api.repository.activity;

import com.saemodong.api.model.activity.Activity;
import java.util.List;

public interface ActivityCustomRepository {

  List<Activity> findExtraBy(
      Integer page,
      String sorter,
      List<Long> typeId,
      List<Long> fieldId,
      List<Long> districtId,
      List<Long> organizerId);

  List<Activity> findContestBy(
      Integer page,
      String sorter,
      List<Long> typeId,
      List<Long> fieldId,
      List<Long> organizerId,
      List<Long> prizeId);
}
