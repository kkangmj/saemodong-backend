package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.extra.ExtraField;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraField;
import com.saemodong.api.repository.activity.extra.ExtraFieldRepository;
import com.saemodong.api.repository.user.interest.UserExtraFieldRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraFieldService extends ActivityCondition {

  private final UserExtraFieldRepository userExtraFieldRepository;
  private final ExtraFieldRepository extraFieldRepository;

  @Override
  public boolean existsByUser(User user) {
    return userExtraFieldRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserExtraField> userExtraFields = userExtraFieldRepository.findAllByUser(user);
    userExtraFieldRepository.deleteAll(userExtraFields);

    if (!condition.isEmpty()) {
      List<Long> fields = dividerHelper.splitByDivider(condition);
      for (Long field : fields) {
        Optional<ExtraField> extraField = extraFieldRepository.findById(field);
        extraField.ifPresent(
            value -> userExtraFieldRepository.save(UserExtraField.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userExtraFieldRepository.findAllByUser(user).stream()
        .map(userExtraField -> userExtraField.getExtraField().getId())
        .collect(Collectors.toList());
  }
}
