package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserExtraField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraFieldRepository
    extends JpaRepository<UserExtraField, Long>, UserInterestBaseRepository<UserExtraField> {}
