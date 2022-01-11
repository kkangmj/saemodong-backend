package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.notification.Notification;
import com.saemodong.api.model.user.InterestActivityScreen;
import com.saemodong.api.model.user.User;
import com.saemodong.api.repository.NotificationRepository;
import com.saemodong.api.repository.activity.ActivityCustomRepository;
import com.saemodong.api.repository.activity.ActivityRepository;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.repository.view.InterestActivityRepository;
import com.saemodong.api.service.user.interest.ContestFieldService;
import com.saemodong.api.service.user.interest.ContestOrganizerService;
import com.saemodong.api.service.user.interest.ContestPrizeService;
import com.saemodong.api.service.user.interest.ContestTypeService;
import com.saemodong.api.service.user.interest.ExtraDistrictService;
import com.saemodong.api.service.user.interest.ExtraFieldService;
import com.saemodong.api.service.user.interest.ExtraOrganizerService;
import com.saemodong.api.service.user.interest.ExtraTypeService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInterestService {

  private final ExtraTypeService extraTypeService;
  private final ExtraFieldService extraFieldService;
  private final ExtraOrganizerService extraOrganizerService;
  private final ExtraDistrictService extraDistrictService;

  private final ContestTypeService contestTypeService;
  private final ContestFieldService contestFieldService;
  private final ContestOrganizerService contestOrganizerService;
  private final ContestPrizeService contestPrizeService;

  private final ActivityCustomRepository activityCustomRepository;
  private final InterestActivityRepository interestActivityScreenRepository;
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;

  @Transactional
  public ExtraInterestDto getUserExtraInterest(User user) {

    String extraTypeStr = extraTypeService.getConditionStr(user);
    String extraFieldStr = extraFieldService.getConditionStr(user);
    String extraOrganizerStr = extraOrganizerService.getConditionStr(user);
    String extraDistrictStr = extraDistrictService.getConditionStr(user);

    return ExtraInterestDto.of(extraTypeStr, extraFieldStr, extraOrganizerStr, extraDistrictStr);
  }

  @Transactional
  public ContestInterestDto getUserContestInterest(User user) {

    String contestTypeStr = contestTypeService.getConditionStr(user);
    String contestFieldStr = contestFieldService.getConditionStr(user);
    String contestOrganizerStr = contestOrganizerService.getConditionStr(user);
    String contestPrizeStr = contestPrizeService.getConditionStr(user);

    return ContestInterestDto.of(
        contestTypeStr, contestFieldStr, contestOrganizerStr, contestPrizeStr);
  }

  @Transactional
  public void updateSetInterest(User user) {

    if (extraTypeService.existsByUser(user)
        || extraFieldService.existsByUser(user)
        || extraOrganizerService.existsByUser(user)
        || extraDistrictService.existsByUser(user)
        || contestTypeService.existsByUser(user)
        || contestFieldService.existsByUser(user)
        || contestOrganizerService.existsByUser(user)
        || contestPrizeService.existsByUser(user)) {
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

    extraTypeService.setCondition(user, type);
    extraFieldService.setCondition(user, field);
    extraOrganizerService.setCondition(user, organizer);
    extraDistrictService.setCondition(user, district);

    updateSetInterest(user);
  }

  @Transactional
  public void setUserContestInterest(
      String apiKey, String type, String field, String organizer, String prize) {

    User user = userRepository.findByApiKey(apiKey).get();

    // TODO !user.isPresent()인 경우 에러

    contestTypeService.setCondition(user, type);
    contestFieldService.setCondition(user, field);
    contestOrganizerService.setCondition(user, organizer);
    contestPrizeService.setCondition(user, prize);

    updateSetInterest(user);
  }


      }
    }
  }
}
