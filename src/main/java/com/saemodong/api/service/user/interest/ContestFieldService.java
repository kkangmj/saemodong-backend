package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.contest.ContestField;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestField;
import com.saemodong.api.repository.activity.contest.ContestFieldRepository;
import com.saemodong.api.repository.user.interest.UserContestFieldRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestFieldService extends ActivityCondition {

  private final UserContestFieldRepository userContestFieldRepository;
  private final ContestFieldRepository contestFieldRepository;

  @Override
  public boolean existsByUser(User user) {
    return userContestFieldRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserContestField> userContestFields = userContestFieldRepository.findAllByUser(user);
    userContestFieldRepository.deleteAll(userContestFields);

    if (!condition.isEmpty()) {
      List<Long> fields = dividerHelper.splitByDivider(condition);
      for (Long field : fields) {
        Optional<ContestField> contestField = contestFieldRepository.findById(field);
        contestField.ifPresent(
            value -> userContestFieldRepository.save(UserContestField.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userContestFieldRepository.findAllByUser(user).stream()
        .map(userContestField -> userContestField.getContestField().getId())
        .collect(Collectors.toList());
  }
}
