package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.contest.ContestType;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestType;
import com.saemodong.api.repository.activity.contest.ContestTypeRepository;
import com.saemodong.api.repository.user.interest.UserContestTypeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestTypeService extends ActivityCondition {

  private final UserContestTypeRepository userContestTypeRepository;
  private final ContestTypeRepository contestTypeRepository;

  @Override
  public boolean existsByUser(User user) {
    return userContestTypeRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserContestType> userContestTypes = userContestTypeRepository.findAllByUser(user);
    userContestTypeRepository.deleteAll(userContestTypes);

    if (!condition.isEmpty()) {
      List<Long> types = dividerHelper.splitByDivider(condition);
      System.out.println(types);
      for (Long type : types) {
        Optional<ContestType> contestType = contestTypeRepository.findById(type);
        contestType.ifPresent(
            value -> userContestTypeRepository.save(UserContestType.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userContestTypeRepository.findAllByUser(user).stream()
        .map(userContestType -> userContestType.getContestType().getId())
        .collect(Collectors.toList());
  }
}
