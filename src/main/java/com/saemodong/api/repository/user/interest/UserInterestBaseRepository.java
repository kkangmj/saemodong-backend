package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserInterestBaseEntity;
import java.util.List;

public interface UserInterestBaseRepository<T extends UserInterestBaseEntity> {
  List<T> findAllByUser(User user);

  boolean existsByUser(User user);
}
