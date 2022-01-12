package com.saemodong.api.repository.view;

import com.saemodong.api.model.view.InterestActivityView;
import com.saemodong.api.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestActivityRepository
    extends JpaRepository<InterestActivityView, Long> {
  Optional<InterestActivityView> findByUser(User user);
}
