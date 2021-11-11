package com.saemodong.api.dto.user;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestInterestDto {
  @NotNull private List<Long> type;
  @NotNull private List<Long> field;
  @NotNull private List<Long> organizer;
  @NotNull private List<Long> prize;
}
