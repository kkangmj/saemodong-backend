package com.saemodong.api.service.user.interest;

import com.saemodong.api.model.activity.extra.ExtraDistrict;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserExtraDistrict;
import com.saemodong.api.repository.activity.extra.ExtraDistrictRepository;
import com.saemodong.api.repository.user.interest.UserExtraDistrictRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraDistrictService extends ActivityCondition {
  private final UserExtraDistrictRepository userExtraDistrictRepository;
  private final ExtraDistrictRepository extraDistrictRepository;

  @Override
  public boolean existsByUser(User user) {
    return userExtraDistrictRepository.existsByUser(user);
  }

  @Override
  public void setCondition(User user, String condition) {
    List<UserExtraDistrict> userExtraDistricts = userExtraDistrictRepository.findAllByUser(user);
    userExtraDistrictRepository.deleteAll(userExtraDistricts);

    if (!condition.isEmpty()) {
      List<Long> districts = dividerHelper.splitByDivider(condition);
      for (Long district : districts) {
        Optional<ExtraDistrict> extraDistrict = extraDistrictRepository.findById(district);
        extraDistrict.ifPresent(
            value -> userExtraDistrictRepository.save(UserExtraDistrict.of(user, value)));
      }
    }
  }

  @Override
  public List<Long> getCondition(User user) {
    return userExtraDistrictRepository.findAllByUser(user).stream()
        .map(userExtraDistrict -> userExtraDistrict.getExtraDistrict().getId())
        .collect(Collectors.toList());
  }
}
