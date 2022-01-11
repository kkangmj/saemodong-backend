package com.saemodong.api.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DividerHelper {

  public String joinByDivider(List<Long> target) {
    return target.isEmpty()
        ? ""
        : target.stream().map(Object::toString).collect(Collectors.joining("+"));
  }

  public List<Long> splitByDivider(String target) {
    return target.isEmpty()
        ? new ArrayList<>()
        : Arrays.stream(target.split("\\+")).map(Long::parseLong).collect(Collectors.toList());
  }
}
