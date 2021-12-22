package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserExtraOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraOrganizerRepository
    extends JpaRepository<UserExtraOrganizer, Long>,
        UserInterestBaseRepository<UserExtraOrganizer> {}
