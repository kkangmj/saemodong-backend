package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.contest.ContestOrganizer;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestOrganizer;
import com.saemodong.api.repository.activity.contest.ContestOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserContestOrganizerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestOrganizerService extends ActivityCondition {

  private final UserContestOrganizerRepository userContestOrganizerRepository;
  private final ContestOrganizerRepository contestOrganizerRepository;


  @Override
  public boolean existsByUser(User user) {
    return userContestOrganizerRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserContestOrganizer> userContestOrganizers =
        userContestOrganizerRepository.findAllByUser(user);
    userContestOrganizerRepository.deleteAll(userContestOrganizers);

    if (!condition.isEmpty()) {
      List<Long> organizers = dividerHelper.splitByDivider(condition);
      for (Long organizer : organizers) {
        Optional<ContestOrganizer> contestOrganizer =
            contestOrganizerRepository.findById(organizer);
        contestOrganizer.ifPresent(
            value -> userContestOrganizerRepository.save(UserContestOrganizer.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userContestOrganizerRepository.findAllByUser(user).stream()
        .map(userContestOrganizer -> userContestOrganizer.getContestOrganizer().getId())
        .collect(Collectors.toList());
  }
}
