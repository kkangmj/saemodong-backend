package com.saemodong.api.service.user;

import com.saemodong.api.model.activity.contest.ContestField;
import com.saemodong.api.model.activity.contest.ContestOrganizer;
import com.saemodong.api.model.activity.contest.ContestPrize;
import com.saemodong.api.model.activity.contest.ContestType;
import com.saemodong.api.model.activity.extra.ExtraDistrict;
import com.saemodong.api.model.activity.extra.ExtraField;
import com.saemodong.api.model.activity.extra.ExtraOrganizer;
import com.saemodong.api.model.activity.extra.ExtraType;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestField;
import com.saemodong.api.model.user.interest.UserContestOrganizer;
import com.saemodong.api.model.user.interest.UserContestPrize;
import com.saemodong.api.model.user.interest.UserContestType;
import com.saemodong.api.model.user.interest.UserExtraDistrict;
import com.saemodong.api.model.user.interest.UserExtraField;
import com.saemodong.api.model.user.interest.UserExtraOrganizer;
import com.saemodong.api.model.user.interest.UserExtraType;
import com.saemodong.api.repository.activity.contest.ContestFieldRepository;
import com.saemodong.api.repository.activity.contest.ContestOrganizerRepository;
import com.saemodong.api.repository.activity.contest.ContestPrizeRepository;
import com.saemodong.api.repository.activity.contest.ContestTypeRepository;
import com.saemodong.api.repository.activity.extra.ExtraDistrictRepository;
import com.saemodong.api.repository.activity.extra.ExtraFieldRepository;
import com.saemodong.api.repository.activity.extra.ExtraOrganizerRepository;
import com.saemodong.api.repository.activity.extra.ExtraTypeRepository;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.repository.user.interest.UserContestFieldRepository;
import com.saemodong.api.repository.user.interest.UserContestOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserContestPrizeRepository;
import com.saemodong.api.repository.user.interest.UserContestTypeRepository;
import com.saemodong.api.repository.user.interest.UserExtraDistrictRepository;
import com.saemodong.api.repository.user.interest.UserExtraFieldRepository;
import com.saemodong.api.repository.user.interest.UserExtraOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserExtraTypeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserInterestService {

  private final UserExtraFieldRepository userExtraFieldRepository;
  private final UserExtraTypeRepository userExtraTypeRepository;
  private final UserExtraOrganizerRepository userExtraOrganizerRepository;
  private final UserExtraDistrictRepository userExtraDistrictRepository;
  private final UserContestTypeRepository userContestTypeRepository;
  private final UserContestFieldRepository userContestFieldRepository;
  private final UserContestOrganizerRepository userContestOrganizerRepository;
  private final UserContestPrizeRepository userContestPrizeRepository;

  private final ExtraFieldRepository extraFieldRepository;
  private final ExtraTypeRepository extraTypeRepository;
  private final ExtraOrganizerRepository extraOrganizerRepository;
  private final ExtraDistrictRepository extraDistrictRepository;
  private final ContestTypeRepository contestTypeRepository;
  private final ContestFieldRepository contestFieldRepository;
  private final ContestOrganizerRepository contestOrganizerRepository;
  private final ContestPrizeRepository contestPrizeRepository;

  private final UserRepository userRepository;

  @Transactional
  public void setUserInterest(
      String apiKey,
      List<Long> extraTypes,
      List<Long> extraFields,
      List<Long> extraOrganizers,
      List<Long> extraDistricts,
      List<Long> contestTypes,
      List<Long> contestFields,
      List<Long> contestOrganizers,
      List<Long> contestPrizes) {

    Optional<User> user = userRepository.findByApiKey(apiKey);

    // TODO !user.isPresent()인 경우 에러

    setExtraTypes(user.get(), extraTypes);
    setExtraFields(user.get(), extraFields);
    setExtraOrganizers(user.get(), extraOrganizers);
    setExtraDistricts(user.get(), extraDistricts);
    setContestTypes(user.get(), contestTypes);
    setContestFields(user.get(), contestFields);
    setContestOrganizers(user.get(), contestOrganizers);
    setContestPrizes(user.get(), contestPrizes);
  }

  private void setExtraTypes(User user, List<Long> extraTypes) {
    List<UserExtraType> userExtraTypes = userExtraTypeRepository.findAllByUser(user);
    userExtraTypeRepository.deleteAll(userExtraTypes);

    for (Long type : extraTypes) {
      Optional<ExtraType> extraType = extraTypeRepository.findById(type);
      extraType.ifPresent(value -> userExtraTypeRepository.save(UserExtraType.of(user, value)));
    }
  }

  private void setExtraFields(User user, List<Long> extraFields) {
    List<UserExtraField> userExtraFields = userExtraFieldRepository.findAllByUser(user);
    userExtraFieldRepository.deleteAll(userExtraFields);

    for (Long field : extraFields) {
      Optional<ExtraField> extraField = extraFieldRepository.findById(field);
      extraField.ifPresent(value -> userExtraFieldRepository.save(UserExtraField.of(user, value)));
    }
  }

  private void setExtraOrganizers(User user, List<Long> extraOrganizers) {
    List<UserExtraOrganizer> userExtraOrganizers = userExtraOrganizerRepository.findAllByUser(user);
    userExtraOrganizerRepository.deleteAll(userExtraOrganizers);

    for (Long organizer : extraOrganizers) {
      Optional<ExtraOrganizer> extraOrganizer = extraOrganizerRepository.findById(organizer);
      extraOrganizer.ifPresent(
          value -> userExtraOrganizerRepository.save(UserExtraOrganizer.of(user, value)));
    }
  }

  private void setExtraDistricts(User user, List<Long> extraDistricts) {
    List<UserExtraDistrict> userExtraDistricts = userExtraDistrictRepository.findAllByUser(user);
    userExtraDistrictRepository.deleteAll(userExtraDistricts);

    for (Long district : extraDistricts) {
      Optional<ExtraDistrict> extraDistrict = extraDistrictRepository.findById(district);
      extraDistrict.ifPresent(
          value -> userExtraDistrictRepository.save(UserExtraDistrict.of(user, value)));
    }
  }

  private void setContestTypes(User user, List<Long> contestTypes) {
    List<UserContestType> userContestTypes = userContestTypeRepository.findAllByUser(user);
    userContestTypeRepository.deleteAll(userContestTypes);

    for (Long type : contestTypes) {
      Optional<ContestType> contestType = contestTypeRepository.findById(type);
      contestType.ifPresent(
          value -> userContestTypeRepository.save(UserContestType.of(user, value)));
    }
  }

  private void setContestFields(User user, List<Long> contestFields) {
    List<UserContestField> userContestFields = userContestFieldRepository.findAllByUser(user);
    userContestFieldRepository.deleteAll(userContestFields);

    for (Long field : contestFields) {
      Optional<ContestField> contestField = contestFieldRepository.findById(field);
      contestField.ifPresent(
          value -> userContestFieldRepository.save(UserContestField.of(user, value)));
    }
  }

  private void setContestOrganizers(User user, List<Long> contestOrganizers) {
    List<UserContestOrganizer> userContestOrganizers =
        userContestOrganizerRepository.findAllByUser(user);
    userContestOrganizerRepository.deleteAll(userContestOrganizers);

    for (Long organizer : contestOrganizers) {
      Optional<ContestOrganizer> contestOrganizer = contestOrganizerRepository.findById(organizer);
      contestOrganizer.ifPresent(
          value -> userContestOrganizerRepository.save(UserContestOrganizer.of(user, value)));
    }
  }

  private void setContestPrizes(User user, List<Long> contestPrizes) {
    List<UserContestPrize> userContestPrizes = userContestPrizeRepository.findAllByUser(user);
    userContestPrizeRepository.deleteAll(userContestPrizes);

    for (Long prize : contestPrizes) {
      Optional<ContestPrize> contestPrize = contestPrizeRepository.findById(prize);
      contestPrize.ifPresent(
          value -> userContestPrizeRepository.save(UserContestPrize.of(user, value)));
    }
  }
}
