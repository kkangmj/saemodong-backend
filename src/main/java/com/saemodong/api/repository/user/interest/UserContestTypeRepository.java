package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestTypeRepository extends JpaRepository<UserContestType, Long> {

  List<UserContestType> findAllByUser(User user);
}
