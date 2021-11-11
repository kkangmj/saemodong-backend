package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestOrganizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContestOrganizerRepository extends JpaRepository<UserContestOrganizer, Long> {

  List<UserContestOrganizer> findAllByUser(User user);
}
