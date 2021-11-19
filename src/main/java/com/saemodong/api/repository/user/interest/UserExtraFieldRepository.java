package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraFieldRepository extends JpaRepository<UserExtraField, Long> {

  List<UserExtraField> findAllByUserId(Long userId);

  List<UserExtraField> findAllByUser(User user);
}
