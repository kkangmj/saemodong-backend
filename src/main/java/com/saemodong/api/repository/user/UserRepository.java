package com.saemodong.api.repository.user;

import com.saemodong.api.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByApiKey(String apiKey);

  Optional<User> findByApiKey(String apiKey);
}
