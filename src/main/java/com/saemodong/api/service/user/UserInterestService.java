package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  private final ExtraTypeRepository extraTypeRepository;
  private final ExtraFieldRepository extraFieldRepository;
  private final ExtraOrganizerRepository extraOrganizerRepository;
  private final ExtraDistrictRepository extraDistrictRepository;
  private final ContestTypeRepository contestTypeRepository;
  private final ContestFieldRepository contestFieldRepository;
  private final ContestOrganizerRepository contestOrganizerRepository;
  private final ContestPrizeRepository contestPrizeRepository;

  private final UserRepository userRepository;

  @Transactional
  public ExtraInterestDto getUserExtraInterest(Long userId) {

    String extraTypeStr = "";
    String extraFieldStr = "";
    String extraOrganizerStr = "";
    String extraDistrictStr = "";

    List<UserExtraType> userExtraTypes = userExtraTypeRepository.findAllByUserId(userId);
    List<UserExtraField> userExtraFields = userExtraFieldRepository.findAllByUserId(userId);
    List<UserExtraOrganizer> userExtraOrganizers =
        userExtraOrganizerRepository.findAllByUserId(userId);
    List<UserExtraDistrict> userExtraDistricts =
        userExtraDistrictRepository.findAllByUserId(userId);

    if (!userExtraTypes.isEmpty()) {
      List<String> extraTypes =
          userExtraTypes.stream()
              .map(item -> Long.toString(item.getExtraType().getId()))
              .collect(Collectors.toList());
      extraTypeStr = String.join("+", extraTypes);
    }

    if (!userExtraFields.isEmpty()) {
      List<String> extraFields =
          userExtraFields.stream()
              .map(item -> Long.toString(item.getExtraField().getId()))
              .collect(Collectors.toList());
      extraFieldStr = String.join("+", extraFields);
    }

    if (!userExtraOrganizers.isEmpty()) {
      List<String> extraOrganizers =
          userExtraOrganizers.stream()
              .map(item -> Long.toString(item.getExtraOrganizer().getId()))
              .collect(Collectors.toList());
      extraOrganizerStr = String.join("+", extraOrganizers);
    }

    if (!userExtraDistricts.isEmpty()) {
      List<String> extraDistricts =
          userExtraDistricts.stream()
              .map(item -> Long.toString(item.getExtraDistrict().getId()))
              .collect(Collectors.toList());
      extraDistrictStr = String.join("+", extraDistricts);
    }

    return ExtraInterestDto.of(extraTypeStr, extraFieldStr, extraOrganizerStr, extraDistrictStr);
  }

  @Transactional
  public ContestInterestDto getUserContestInterest(Long userId) {

    String contestTypeStr = "";
    String contestFieldStr = "";
    String contestOrganizerStr = "";
    String contestPrizeStr = "";

    List<UserContestType> userContestTypes = userContestTypeRepository.findAllByUserId(userId);
    List<UserContestField> userContestFields = userContestFieldRepository.findAllByUserId(userId);
    List<UserContestOrganizer> userContestOrganizers =
        userContestOrganizerRepository.findAllByUserId(userId);
    List<UserContestPrize> userContestPrizes = userContestPrizeRepository.findAllByUserId(userId);

    if (!userContestTypes.isEmpty()) {
      List<String> contestTypes =
          userContestTypes.stream()
              .map(item -> Long.toString(item.getContestType().getId()))
              .collect(Collectors.toList());
      contestTypeStr = String.join("+", contestTypes);
    }

    if (!userContestFields.isEmpty()) {
      List<String> contestFields =
          userContestFields.stream()
              .map(item -> Long.toString(item.getContestField().getId()))
              .collect(Collectors.toList());
      contestFieldStr = String.join("+", contestFields);
    }

    if (!userContestOrganizers.isEmpty()) {
      List<String> contestOrganizers =
          userContestOrganizers.stream()
              .map(item -> Long.toString(item.getContestOrganizer().getId()))
              .collect(Collectors.toList());
      contestOrganizerStr = String.join("+", contestOrganizers);
    }

    if (!userContestPrizes.isEmpty()) {
      List<String> contestPrizes =
          userContestPrizes.stream()
              .map(item -> Long.toString(item.getContestPrize().getId()))
              .collect(Collectors.toList());
      contestPrizeStr = String.join("+", contestPrizes);
    }

    return ContestInterestDto.of(
        contestTypeStr, contestFieldStr, contestOrganizerStr, contestPrizeStr);
  }

  @Transactional
  public void setUserExtraInterest(
      String apiKey, String type, String field, String organizer, String district) {

    User user = userRepository.findByApiKey(apiKey).get();

    // TODO !user.isPresent()인 경우 에러
    // TODO setInterest 여부 바꿔야 함.

    setExtraTypes(user, type);
    setExtraFields(user, field);
    setExtraOrganizers(user, organizer);
    setExtraDistricts(user, district);
  }

  @Transactional
  public void setUserContestInterest(
      String apiKey, String type, String field, String organizer, String prize) {

    User user = userRepository.findByApiKey(apiKey).get();

    // TODO !user.isPresent()인 경우 에러
    // TODO setInterest 여부 바꿔야 함.

    setContestTypes(user, type);
    setContestFields(user, field);
    setContestOrganizers(user, organizer);
    setContestPrizes(user, prize);
  }

  private List<Long> splitByDivider(String target) {

    return Arrays.asList(target.split("\\+")).stream()
        .map(Long::parseLong)
        .collect(Collectors.toList());
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
}
