package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestField;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestFieldRepository extends JpaRepository<UserContestField, Long> {

  List<UserContestField> findAllByUser(User user);

  List<UserContestField> findAllByUserId(Long userId);
}
