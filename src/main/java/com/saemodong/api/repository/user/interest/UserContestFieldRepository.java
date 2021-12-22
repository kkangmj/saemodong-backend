package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserContestField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestFieldRepository
    extends JpaRepository<UserContestField, Long>, UserInterestBaseRepository<UserContestField> {}
