package com.saemodong.api.service.user;

import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;

public interface UserInterestService {
  ExtraInterestDto getUserExtraInterest(String apiKey);

  ContestInterestDto getUserContestInterest(String apiKey);

  void setUserExtraInterest(
      String apiKey, String type, String field, String organizer, String district);

  void setUserContestInterest(String apiKey, String type, String field, String organizer, String prize);


}
