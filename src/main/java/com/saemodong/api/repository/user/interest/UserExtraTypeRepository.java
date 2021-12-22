package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserExtraType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraTypeRepository
    extends JpaRepository<UserExtraType, Long>, UserInterestBaseRepository<UserExtraType> {}
