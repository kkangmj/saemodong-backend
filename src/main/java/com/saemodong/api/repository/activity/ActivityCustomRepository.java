package com.saemodong.api.repository.activity;

import com.saemodong.api.model.activity.Activity;
import java.util.List;

public interface ActivityCustomRepository {
  List<Activity> findAllBy(
      Integer page,
      String sorter,
      List<Long> typeId,
      List<Long> fieldId,
      List<Long> districtId,
      List<Long> organizerId);
}
