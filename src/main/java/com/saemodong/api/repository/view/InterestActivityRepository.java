package com.saemodong.api.repository.view;

import com.saemodong.api.model.user.InterestActivityScreen;
import com.saemodong.api.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestActivityRepository
    extends JpaRepository<InterestActivityScreen, Long> {
  Optional<InterestActivityScreen> findByUser(User user);
}
