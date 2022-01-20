package com.saemodong.api.controller.user;

import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;
import com.saemodong.api.service.user.UserInterestService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/user/interest")
public class UserInterestController {

  private UserInterestService userInterestService;

  @GetMapping("extra")
  public ResponseEntity<? extends ApiResponse> getExtraInterest(@RequestParam String apiKey) {

    ExtraInterestDto extraInterestDto = userInterestService.getUserExtraInterest(apiKey);

    return ResponseEntity.ok(SuccessResponse.of(extraInterestDto));
  }

  @GetMapping("contest")
  public ResponseEntity<? extends ApiResponse> getContestInterest(@RequestParam String apiKey) {

    ContestInterestDto contestInterestDto = userInterestService.getUserContestInterest(apiKey);
    return ResponseEntity.ok(SuccessResponse.of(contestInterestDto));
  }

  @PutMapping("extra")
  public ResponseEntity<? extends ApiResponse> setExtraInterest(
      @RequestParam String apiKey, @RequestBody @Valid ExtraInterestDto extraInterestDto) {

    userInterestService.setUserExtraInterest(
        apiKey,
        extraInterestDto.getType(),
        extraInterestDto.getField(),
        extraInterestDto.getOrganizer(),
        extraInterestDto.getDistrict());

    return ResponseEntity.noContent().build();
  }

  @PutMapping("contest")
  public ResponseEntity<? extends ApiResponse> setContestInterest(
      @RequestParam String apiKey, @RequestBody @Valid ContestInterestDto contestInterestDto) {

    userInterestService.setUserContestInterest(
        apiKey,
        contestInterestDto.getType(),
        contestInterestDto.getField(),
        contestInterestDto.getOrganizer(),
        contestInterestDto.getPrize());

    return ResponseEntity.noContent().build();
  }
}
