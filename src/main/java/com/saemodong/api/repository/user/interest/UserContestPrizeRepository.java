package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestPrize;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestPrizeRepository extends JpaRepository<UserContestPrize, Long> {

  List<UserContestPrize> findAllByUser(User user);

  List<UserContestPrize> findAllByUserId(Long userId);
}
