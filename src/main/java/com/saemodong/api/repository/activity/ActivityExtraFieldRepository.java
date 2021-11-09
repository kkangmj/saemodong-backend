package com.saemodong.api.repository.activity;

import com.saemodong.api.model.activity.extra.ActivityExtraField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityExtraFieldRepository extends JpaRepository<ActivityExtraField, Long> {

  List<ActivityExtraField> findAllByActivityId(Long activityId);
}
