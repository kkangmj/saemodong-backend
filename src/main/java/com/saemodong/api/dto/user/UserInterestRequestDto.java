package com.saemodong.api.dto.user;

import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInterestRequestDto {
  @Valid private ExtraInterestDto extraInterestDto;
  @Valid private ContestInterestDto contestInterestDto;
}
