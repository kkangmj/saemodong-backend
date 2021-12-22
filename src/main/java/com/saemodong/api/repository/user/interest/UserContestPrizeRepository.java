package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserContestPrize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestPrizeRepository
    extends JpaRepository<UserContestPrize, Long>, UserInterestBaseRepository<UserContestPrize> {}
