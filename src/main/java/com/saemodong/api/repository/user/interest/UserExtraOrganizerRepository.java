package com.saemodong.api.repository.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraOrganizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraOrganizerRepository extends JpaRepository<UserExtraOrganizer, Long> {

  List<UserExtraOrganizer> findAllByUser(User user);

  List<UserExtraOrganizer> findAllByUserId(Long userId);
}
