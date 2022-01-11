package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.contest.ContestPrize;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestPrize;
import com.saemodong.api.repository.activity.contest.ContestPrizeRepository;
import com.saemodong.api.repository.user.interest.UserContestPrizeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestPrizeService extends ActivityCondition {

  private final UserContestPrizeRepository userContestPrizeRepository;
  private final ContestPrizeRepository contestPrizeRepository;

  @Override
  public boolean existsByUser(User user) {
    return userContestPrizeRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserContestPrize> userContestPrizes = userContestPrizeRepository.findAllByUser(user);
    userContestPrizeRepository.deleteAll(userContestPrizes);

    if (!condition.isEmpty()) {
      List<Long> prizes = dividerHelper.splitByDivider(condition);
      for (Long prize : prizes) {
        Optional<ContestPrize> contestPrize = contestPrizeRepository.findById(prize);
        contestPrize.ifPresent(
            value -> userContestPrizeRepository.save(UserContestPrize.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userContestPrizeRepository.findAllByUser(user).stream()
        .map(userContestPrize -> userContestPrize.getContestPrize().getId())
        .collect(Collectors.toList());
  }
}
