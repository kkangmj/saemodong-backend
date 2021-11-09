package com.saemodong.api.repository.activity;

import com.saemodong.api.model.activity.contest.ActivityContestField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityContestFieldRepository extends JpaRepository<ActivityContestField, Long> {

  List<ActivityContestField> findAllByActivityId(Long activityId);
}
