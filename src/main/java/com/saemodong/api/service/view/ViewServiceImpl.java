package com.saemodong.api.service.view;

import static org.springframework.data.util.Optionals.ifPresentOrElse;

import com.saemodong.api.common.LocalDateTimeFormatter;
import com.saemodong.api.exception.UserNotFoundException;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.view.InterestActivityView;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.repository.view.InterestActivityRepository;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewServiceImpl implements ViewService {

  private final UserRepository userRepository;
  private final InterestActivityRepository interestActivityRepository;

  private final LocalDateTimeFormatter localDateTimeFormatter;
s
  public String getLastVisitedAt(String apiKey) {

    User user =
        userRepository.findByApiKey(apiKey).orElseThrow(() -> new UserNotFoundException(apiKey));

    String lastVisitedAt =
        interestActivityRepository
            .findByUser(user)
            .map(
                interestActivityScreen ->
                    localDateTimeFormatter.localDateTimeToString(
                        interestActivityScreen.getLastVisitedAt()))
            .orElse("");

    return lastVisitedAt;
  }

  public void setLastVisitedAt(String apiKey, LocalDateTime localDateTime) {

    User user =
        userRepository.findByApiKey(apiKey).orElseThrow(() -> new UserNotFoundException(apiKey));

    ifPresentOrElse(
        interestActivityRepository.findByUser(user),
        screen -> {
          screen.updateLastVisitedAt(localDateTime);
          interestActivityRepository.save(screen);
        },
        () -> interestActivityRepository.save(InterestActivityView.of(user, localDateTime)));
  }
}
