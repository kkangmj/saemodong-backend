package com.saemodong.api.dto.user;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestInterestDto {
  @NotNull private String type;
  @NotNull private String field;
  @NotNull private String organizer;
  @NotNull private String prize;

  public static ContestInterestDto of(String type, String field, String organizer, String prize) {
    return new ContestInterestDto(type, field, organizer, prize);
  }
}
