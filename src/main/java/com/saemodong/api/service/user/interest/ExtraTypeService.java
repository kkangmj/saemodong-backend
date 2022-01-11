package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraType;
import com.saemodong.api.repository.activity.extra.ExtraTypeRepository;
import com.saemodong.api.repository.user.interest.UserExtraTypeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraTypeService extends ActivityCondition {

  private final UserExtraTypeRepository userExtraTypeRepository;
  private final ExtraTypeRepository extraTypeRepository;

  @Override
  public boolean existsByUser(User user) {
    return userExtraTypeRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserExtraType> userExtraTypes = userExtraTypeRepository.findAllByUser(user);
    userExtraTypeRepository.deleteAll(userExtraTypes);
    if (!condition.isEmpty()) {
      List<Long> types = dividerHelper.splitByDivider(condition);
      for (Long type : types) {
        Optional<com.saemodong.api.model.activity.extra.ExtraType> extraType =
            extraTypeRepository.findById(type);
        extraType.ifPresent(value -> userExtraTypeRepository.save(UserExtraType.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userExtraTypeRepository.findAllByUser(user).stream()
        .map(userExtraType -> userExtraType.getExtraType().getId())
        .collect(Collectors.toList());
  }
}
