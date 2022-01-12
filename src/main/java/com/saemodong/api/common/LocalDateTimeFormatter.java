package com.saemodong.api.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class LocalDateTimeFormatter {

  public String localDateTimeToString(LocalDateTime localDateTime) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime)
        + "T"
        + DateTimeFormatter.ofPattern("HH:mm:ss").format(localDateTime);
  }
}
