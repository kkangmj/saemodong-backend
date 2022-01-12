package com.saemodong.api.controller.view;

import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.dto.view.InterestActivityDto;
import com.saemodong.api.service.view.ViewService;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/view")
public class ViewController {

  private final ViewService viewService;

  @GetMapping("interest-activity")
  public ResponseEntity<? extends ApiResponse> getInterestActivityView(
      @RequestParam String apiKey) {

    String lastVisitedAt = viewService.getLastVisitedAt(apiKey);

    return ResponseEntity.ok(SuccessResponse.of(InterestActivityDto.of(lastVisitedAt)));
  }

  @PutMapping("interest-activity")
  @Transactional
  public ResponseEntity<? extends ApiResponse> setInterestActivityView(
      @RequestParam String apiKey,
      @RequestParam
          @Pattern(
              regexp =
                  "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])T(0[0-9]|1[0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])$")
          String lastVisitedAt) {

    LocalDateTime localDateTime = LocalDateTime.parse(lastVisitedAt);
    viewService.setLastVisitedAt(apiKey, localDateTime);

    return ResponseEntity.noContent().build();
  }
}
