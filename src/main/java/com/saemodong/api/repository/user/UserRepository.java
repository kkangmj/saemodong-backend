package com.saemodong.api.repository.user;

import com.saemodong.api.model.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByApiKey(String apiKey);

  boolean existsByNickname(String nickname);

  Optional<User> findByApiKey(String apiKey);

  List<User> findAllByIsDeletedAndSetInterest(String isDeleted, String setInterest);
}
