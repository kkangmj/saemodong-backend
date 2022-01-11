package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.extra.ExtraOrganizer;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraOrganizer;
import com.saemodong.api.repository.activity.extra.ExtraOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserExtraOrganizerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraOrganizerService extends ActivityCondition {

  private final UserExtraOrganizerRepository userExtraOrganizerRepository;
  private final ExtraOrganizerRepository extraOrganizerRepository;

  @Override
  public boolean existsByUser(User user) {
    return userExtraOrganizerRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserExtraOrganizer> userExtraOrganizers = userExtraOrganizerRepository.findAllByUser(user);
    userExtraOrganizerRepository.deleteAll(userExtraOrganizers);

    if (!condition.isEmpty()) {
      List<Long> organizers = dividerHelper.splitByDivider(condition);
      for (Long organizer : organizers) {
        Optional<ExtraOrganizer> extraOrganizer = extraOrganizerRepository.findById(organizer);
        extraOrganizer.ifPresent(
            value -> userExtraOrganizerRepository.save(UserExtraOrganizer.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userExtraOrganizerRepository.findAllByUser(user).stream()
        .map(userExtraOrganizer -> userExtraOrganizer.getExtraOrganizer().getId())
        .collect(Collectors.toList());
  }
}
