package com.saemodong.api.repository;

import com.saemodong.api.model.notification.Notification;
import com.saemodong.api.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

  Optional<Notification> findByUserOrderByIsSentDesc(User user);
}
