package com.saemodong.api.controller;

import com.saemodong.api.common.ValuesAllowed;
import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.service.BookmarkService;
import com.saemodong.api.service.activity.ActivityPageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/bookmark")
@Validated
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping("")
  public ResponseEntity<? extends ApiResponse> getBookmark(
      @RequestParam String apiKey,
      @RequestParam Integer page,
      @ValuesAllowed(
              propName = "prop",
              values = {"activity", "team"})
          @RequestParam
          String type) {

    ActivityPageResponse activityPageResponse = bookmarkService.getBookmark(page, type, apiKey);

    return ResponseEntity.ok(SuccessResponse.of(activityPageResponse));
  }
}
