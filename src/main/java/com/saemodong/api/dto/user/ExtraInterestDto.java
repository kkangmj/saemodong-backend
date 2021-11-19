package com.saemodong.api.dto.user;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraInterestDto {
  @NotNull private String type;
  @NotNull private String field;
  @NotNull private String organizer;
  @NotNull private String district;

  public static ExtraInterestDto of(String type, String field, String organizer, String district) {
    return new ExtraInterestDto(type, field, organizer, district);
  }
}
