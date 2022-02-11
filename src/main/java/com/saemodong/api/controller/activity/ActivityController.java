package com.saemodong.api.controller.activity;

import com.saemodong.api.common.ValuesAllowed;
import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.PaginationResponseDto;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.model.bookmark.BookmarkType;
import com.saemodong.api.service.activity.ActivityService;
import com.saemodong.api.service.bookmark.BookmarkService;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/activity")
@Validated
public class ActivityController {

  private final ActivityService activityService;
  private final BookmarkService bookmarkService;

  @GetMapping("")
  public ResponseEntity<? extends ApiResponse> getActivity(
      @RequestParam String apiKey,
      @RequestParam @NotNull @Min(value = 0) Integer pageNumber,
      @RequestParam(defaultValue = "latestAsc")
          @ValuesAllowed(
              propName = "sorter",
              values = {"latestAsc", "ddayAsc"})
          String sorter,
      @RequestParam(defaultValue = "N")
          @ValuesAllowed(
              propName = "registeredToday",
              values = {"Y", "N"})
          String registeredToday) {

    PaginationResponseDto activityInSinglePage =
        activityService.getActivityWithPageNumber(pageNumber, sorter, registeredToday, apiKey);

    return ResponseEntity.ok(SuccessResponse.of(activityInSinglePage));
  }

  @GetMapping("/extra")
  public ResponseEntity<? extends ApiResponse> getActivityExtra(
      @RequestParam String apiKey,
      @RequestParam Integer page,
      @ValuesAllowed(
              propName = "sorter",
              values = {"latestAsc", "ddayAsc"})
          @RequestParam(required = false, defaultValue = "latestAsc")
          String sorter,
      @RequestParam(required = false, defaultValue = "") String type,
      @RequestParam(required = false, defaultValue = "") String field,
      @RequestParam(required = false, defaultValue = "") String organizer,
      @RequestParam(required = false, defaultValue = "") String district) {

    PaginationResponseDto activityPaginationResponse =
        activityService.getActivityExtraList(
            page, sorter, type, field, organizer, district, apiKey);

    return ResponseEntity.ok(SuccessResponse.of(activityPaginationResponse));
  }

  @GetMapping("/contest")
  public ResponseEntity<? extends ApiResponse> getActivityContest(
      @RequestParam String apiKey,
      @RequestParam Integer page,
      @ValuesAllowed(
              propName = "sorter",
              values = {"latestAsc", "ddayAsc"})
          @RequestParam(required = false, defaultValue = "latestAsc")
          String sorter,
      @RequestParam(required = false, defaultValue = "") String type,
      @RequestParam(required = false, defaultValue = "") String field,
      @RequestParam(required = false, defaultValue = "") String organizer,
      @RequestParam(required = false, defaultValue = "") String prize) {

    PaginationResponseDto activityPageResponse =
        activityService.getActivityContestList(page, sorter, type, field, organizer, prize, apiKey);

    return ResponseEntity.ok(SuccessResponse.of(activityPageResponse));
  }

  @GetMapping("/{activityId}/mark")
  public ResponseEntity<? extends ApiResponse> markActivity(
      @PathVariable Long activityId, @RequestParam String apiKey) {

    bookmarkService.mark(BookmarkType.ACTIVITY.getType(), activityId, apiKey);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{activityId}/unmark")
  public ResponseEntity<? extends ApiResponse> unmarkActivity(
      @PathVariable Long activityId, @RequestParam String apiKey) {

    bookmarkService.unmark(BookmarkType.ACTIVITY.getType(), activityId, apiKey);
    return ResponseEntity.noContent().build();
  }
}
