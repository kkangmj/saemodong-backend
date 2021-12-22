package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserContestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestTypeRepository
    extends JpaRepository<UserContestType, Long>, UserInterestBaseRepository<UserContestType> {}
