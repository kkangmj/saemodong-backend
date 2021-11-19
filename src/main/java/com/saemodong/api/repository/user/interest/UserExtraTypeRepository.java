package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraTypeRepository extends JpaRepository<UserExtraType, Long> {
  List<UserExtraType> findAllByUser(User user);

  List<UserExtraType> findAllByUserId(Long userId);
}
