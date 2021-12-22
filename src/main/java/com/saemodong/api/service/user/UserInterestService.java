package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.activity.contest.ContestField;
import com.saemodong.api.model.activity.contest.ContestOrganizer;
import com.saemodong.api.model.activity.contest.ContestPrize;
import com.saemodong.api.model.activity.contest.ContestType;
import com.saemodong.api.model.activity.extra.ExtraDistrict;
import com.saemodong.api.model.activity.extra.ExtraField;
import com.saemodong.api.model.activity.extra.ExtraOrganizer;
import com.saemodong.api.model.activity.extra.ExtraType;
import com.saemodong.api.model.notification.Notification;
import com.saemodong.api.model.user.InterestActivityScreen;
import com.saemodong.api.model.user.User;
import com.saemodong.api.model.user.interest.UserContestField;
import com.saemodong.api.model.user.interest.UserContestOrganizer;
import com.saemodong.api.model.user.interest.UserContestPrize;
import com.saemodong.api.model.user.interest.UserContestType;
import com.saemodong.api.model.user.interest.UserExtraDistrict;
import com.saemodong.api.model.user.interest.UserExtraField;
import com.saemodong.api.model.user.interest.UserExtraOrganizer;
import com.saemodong.api.model.user.interest.UserExtraType;
import com.saemodong.api.model.user.interest.UserInterestBaseEntity;
import com.saemodong.api.repository.NotificationRepository;
import com.saemodong.api.repository.activity.ActivityCustomRepository;
import com.saemodong.api.repository.activity.ActivityRepository;
import com.saemodong.api.repository.activity.contest.ContestFieldRepository;
import com.saemodong.api.repository.activity.contest.ContestOrganizerRepository;
import com.saemodong.api.repository.activity.contest.ContestPrizeRepository;
import com.saemodong.api.repository.activity.contest.ContestTypeRepository;
import com.saemodong.api.repository.activity.extra.ExtraDistrictRepository;
import com.saemodong.api.repository.activity.extra.ExtraFieldRepository;
import com.saemodong.api.repository.activity.extra.ExtraOrganizerRepository;
import com.saemodong.api.repository.activity.extra.ExtraTypeRepository;
import com.saemodong.api.repository.user.InterestActivityScreenRepository;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.repository.user.interest.UserContestFieldRepository;
import com.saemodong.api.repository.user.interest.UserContestOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserContestPrizeRepository;
import com.saemodong.api.repository.user.interest.UserContestTypeRepository;
import com.saemodong.api.repository.user.interest.UserExtraDistrictRepository;
import com.saemodong.api.repository.user.interest.UserExtraFieldRepository;
import com.saemodong.api.repository.user.interest.UserExtraOrganizerRepository;
import com.saemodong.api.repository.user.interest.UserExtraTypeRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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

  private final ExtraTypeRepository extraTypeRepository;
  private final ExtraFieldRepository extraFieldRepository;
  private final ExtraOrganizerRepository extraOrganizerRepository;
  private final ExtraDistrictRepository extraDistrictRepository;
  private final ContestTypeRepository contestTypeRepository;
  private final ContestFieldRepository contestFieldRepository;
  private final ContestOrganizerRepository contestOrganizerRepository;
  private final ContestPrizeRepository contestPrizeRepository;

  private final ActivityCustomRepository activityCustomRepository;
  private final InterestActivityScreenRepository interestActivityScreenRepository;
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;

  @Transactional
  public ExtraInterestDto getUserExtraInterest(User user) {

    List<Long> extraTypes = getExtraTypes(user);
    String extraTypeStr = joinByDivider(extraTypes);

    List<Long> extraFields = getExtraFields(user);
    String extraFieldStr = joinByDivider(extraFields);

    List<Long> extraOrganizers = getExtraOrganizer(user);
    String extraOrganizerStr = joinByDivider(extraOrganizers);

    List<Long> extraDistricts = getExtraDistrict(user);
    String extraDistrictStr = joinByDivider(extraDistricts);

    return ExtraInterestDto.of(extraTypeStr, extraFieldStr, extraOrganizerStr, extraDistrictStr);
  }

  @Transactional
  public ContestInterestDto getUserContestInterest(User user) {

    List<Long> contestTypes = getContestType(user);
    String contestTypeStr = joinByDivider(contestTypes);

    List<Long> contestFields = getContestField(user);
    String contestFieldStr = joinByDivider(contestFields);

    List<Long> contestOrganizers = getContestOrganizer(user);
    String contestOrganizerStr = joinByDivider(contestOrganizers);

    List<Long> contestPrizes = getContestPrize(user);
    String contestPrizeStr = joinByDivider(contestPrizes);

    return ContestInterestDto.of(
        contestTypeStr, contestFieldStr, contestOrganizerStr, contestPrizeStr);
  }

  @Transactional
  public void updateSetInterest(User user) {

    if (userExtraTypeRepository.existsByUser(user)
        || userExtraFieldRepository.existsByUser(user)
        || userExtraOrganizerRepository.existsByUser(user)
        || userExtraDistrictRepository.existsByUser(user)
        || userContestTypeRepository.existsByUser(user)
        || userContestFieldRepository.existsByUser(user)
        || userContestOrganizerRepository.existsByUser(user)
        || userContestPrizeRepository.existsByUser(user)) {
      user.updateSetInterest("Y");
    } else {
      user.updateSetInterest("N");
    }
    userRepository.save(user);
  }

  @Transactional
  public void setUserExtraInterest(
      String apiKey, String type, String field, String organizer, String district) {

    User user = userRepository.findByApiKey(apiKey).get();

    // TODO !user.isPresent()인 경우 에러

    setExtraTypes(user, type);
    setExtraFields(user, field);
    setExtraOrganizers(user, organizer);
    setExtraDistricts(user, district);

    updateSetInterest(user);
  }

  @Transactional
  public void setUserContestInterest(
      String apiKey, String type, String field, String organizer, String prize) {

    User user = userRepository.findByApiKey(apiKey).get();

    // TODO !user.isPresent()인 경우 에러

    setContestTypes(user, type);
    setContestFields(user, field);
    setContestOrganizers(user, organizer);
    setContestPrizes(user, prize);

    updateSetInterest(user);
  }

  private List<Long> splitByDivider(String target) {
    return Arrays.asList(target.split("\\+")).stream()
        .map(Long::parseLong)
        .collect(Collectors.toList());
  }

  private String joinByDivider(List<Long> target) {
    return target.isEmpty()
        ? ""
        : String.join("+", target.stream().map(Object::toString).collect(Collectors.toList()));
  }

  private void setExtraTypes(User user, String extraTypes) {
    List<UserExtraType> userExtraTypes = userExtraTypeRepository.findAllByUser(user);
    userExtraTypeRepository.deleteAll(userExtraTypes);

    if (!extraTypes.isEmpty()) {
      List<Long> types = splitByDivider(extraTypes);
      for (Long type : types) {
        Optional<ExtraType> extraType = extraTypeRepository.findById(type);
        extraType.ifPresent(value -> userExtraTypeRepository.save(UserExtraType.of(user, value)));
      }
    }
  }

  private List<Long> getExtraTypes(User user) {
    return userExtraTypeRepository.findAllByUser(user).stream()
        .map(userExtraType -> userExtraType.getExtraType().getId())
        .collect(Collectors.toList());
  }

  private void setExtraFields(User user, String extraFields) {
    List<UserExtraField> userExtraFields = userExtraFieldRepository.findAllByUser(user);
    userExtraFieldRepository.deleteAll(userExtraFields);

    if (!extraFields.isEmpty()) {
      List<Long> fields = splitByDivider(extraFields);
      for (Long field : fields) {
        Optional<ExtraField> extraField = extraFieldRepository.findById(field);
        extraField.ifPresent(
            value -> userExtraFieldRepository.save(UserExtraField.of(user, value)));
      }
    }
  }

  private List<Long> getExtraFields(User user) {
    return userExtraFieldRepository.findAllByUser(user).stream()
        .map(userExtraField -> userExtraField.getExtraField().getId())
        .collect(Collectors.toList());
  }

  private void setExtraOrganizers(User user, String extraOrganizers) {
    List<UserExtraOrganizer> userExtraOrganizers = userExtraOrganizerRepository.findAllByUser(user);
    userExtraOrganizerRepository.deleteAll(userExtraOrganizers);

    if (!extraOrganizers.isEmpty()) {
      List<Long> organizers = splitByDivider(extraOrganizers);
      for (Long organizer : organizers) {
        Optional<ExtraOrganizer> extraOrganizer = extraOrganizerRepository.findById(organizer);
        extraOrganizer.ifPresent(
            value -> userExtraOrganizerRepository.save(UserExtraOrganizer.of(user, value)));
      }
    }
  }

  private List<Long> getExtraOrganizer(User user) {
    return userExtraOrganizerRepository.findAllByUser(user).stream()
        .map(userExtraOrganizer -> userExtraOrganizer.getExtraOrganizer().getId())
        .collect(Collectors.toList());
  }

  private void setExtraDistricts(User user, String extraDistricts) {
    List<UserExtraDistrict> userExtraDistricts = userExtraDistrictRepository.findAllByUser(user);
    userExtraDistrictRepository.deleteAll(userExtraDistricts);

    if (!extraDistricts.isEmpty()) {
      List<Long> districts = splitByDivider(extraDistricts);
      for (Long district : districts) {
        Optional<ExtraDistrict> extraDistrict = extraDistrictRepository.findById(district);
        extraDistrict.ifPresent(
            value -> userExtraDistrictRepository.save(UserExtraDistrict.of(user, value)));
      }
    }
  }

  private List<Long> getExtraDistrict(User user) {
    return userExtraDistrictRepository.findAllByUser(user).stream()
        .map(userExtraDistrict -> userExtraDistrict.getExtraDistrict().getId())
        .collect(Collectors.toList());
  }

  private void setContestTypes(User user, String contestTypes) {
    List<UserContestType> userContestTypes = userContestTypeRepository.findAllByUser(user);
    userContestTypeRepository.deleteAll(userContestTypes);

    if (!contestTypes.isEmpty()) {
      List<Long> types = splitByDivider(contestTypes);
      System.out.println(types);
      for (Long type : types) {
        Optional<ContestType> contestType = contestTypeRepository.findById(type);
        contestType.ifPresent(
            value -> userContestTypeRepository.save(UserContestType.of(user, value)));
      }
    }
  }

  private List<Long> getContestType(User user) {
    return userContestTypeRepository.findAllByUser(user).stream()
        .map(userContestType -> userContestType.getContestType().getId())
        .collect(Collectors.toList());
  }

  private void setContestFields(User user, String contestFields) {
    List<UserContestField> userContestFields = userContestFieldRepository.findAllByUser(user);
    userContestFieldRepository.deleteAll(userContestFields);

    if (!contestFields.isEmpty()) {
      List<Long> fields = splitByDivider(contestFields);
      for (Long field : fields) {
        Optional<ContestField> contestField = contestFieldRepository.findById(field);
        contestField.ifPresent(
            value -> userContestFieldRepository.save(UserContestField.of(user, value)));
      }
    }
  }

  private List<Long> getContestField(User user) {
    return userContestFieldRepository.findAllByUser(user).stream()
        .map(userContestField -> userContestField.getContestField().getId())
        .collect(Collectors.toList());
  }

  private void setContestOrganizers(User user, String contestOrganizers) {
    List<UserContestOrganizer> userContestOrganizers =
        userContestOrganizerRepository.findAllByUser(user);
    userContestOrganizerRepository.deleteAll(userContestOrganizers);

    if (!contestOrganizers.isEmpty()) {
      List<Long> organizers = splitByDivider(contestOrganizers);
      for (Long organizer : organizers) {
        Optional<ContestOrganizer> contestOrganizer =
            contestOrganizerRepository.findById(organizer);
        contestOrganizer.ifPresent(
            value -> userContestOrganizerRepository.save(UserContestOrganizer.of(user, value)));
      }
    }
  }

  private List<Long> getContestOrganizer(User user) {
    return userContestOrganizerRepository.findAllByUser(user).stream()
        .map(userContestOrganizer -> userContestOrganizer.getContestOrganizer().getId())
        .collect(Collectors.toList());
  }

  private void setContestPrizes(User user, String contestPrizes) {
    List<UserContestPrize> userContestPrizes = userContestPrizeRepository.findAllByUser(user);
    userContestPrizeRepository.deleteAll(userContestPrizes);

    if (!contestPrizes.isEmpty()) {
      List<Long> prizes = splitByDivider(contestPrizes);
      for (Long prize : prizes) {
        Optional<ContestPrize> contestPrize = contestPrizeRepository.findById(prize);
        contestPrize.ifPresent(
            value -> userContestPrizeRepository.save(UserContestPrize.of(user, value)));
      }
    }
  }

  private List<Long> getContestPrize(User user) {
    return userContestPrizeRepository.findAllByUser(user).stream()
        .map(userContestPrize -> userContestPrize.getContestPrize().getId())
        .collect(Collectors.toList());
  }
}
