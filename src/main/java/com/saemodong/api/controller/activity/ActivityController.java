package com.saemodong.api.controller.activity;

import com.saemodong.api.dto.BaseResponse;
import com.saemodong.api.dto.ResultCode;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.service.activity.ActivityPageResponse;
import com.saemodong.api.service.activity.ActivityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/activity")
@Validated
public class ActivityController {

  private final ActivityService activityService;

  @GetMapping("")
  public ResponseEntity<? extends BaseResponse> getActivity(
      @RequestParam Integer page,
      @ValuesAllowed(
              propName = "sorter",
              values = {"latestAsc", "ddayAsc"})
          @RequestParam(required = false, defaultValue = "latestAsc")
          String sorter,
      @ValuesAllowed(
              propName = "isToday",
              values = {"Y", "N"})
          @RequestParam(required = false, defaultValue = "N")
          String isToday) {

    ActivityPageResponse activityPageResponse =
        activityService.getActivityList(page, sorter, isToday);

    return ResponseEntity.ok(SuccessResponse.of(ResultCode.OK, activityPageResponse));
  }
}
