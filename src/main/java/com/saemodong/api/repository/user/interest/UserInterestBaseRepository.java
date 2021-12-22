package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserActivityCondition;
import java.util.List;

public interface UserActivityConditionRepository<T extends UserActivityCondition> {
  List<T> findAllByUser(User user);

  boolean existsByUser(User user);
}
