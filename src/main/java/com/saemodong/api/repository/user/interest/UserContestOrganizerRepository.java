package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.interest.UserContestOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestOrganizerRepository
    extends JpaRepository<UserContestOrganizer, Long>,
        UserInterestBaseRepository<UserContestOrganizer> {}
