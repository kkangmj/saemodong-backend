package com.saemodong.api.service.activity;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.mapper.ActivityFieldMapper;
import com.saemodong.api.mapper.ActivityMapper;
import com.saemodong.api.model.BookmarkType;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.repository.BookmarkRepository;
import com.saemodong.api.repository.activity.ActivityCustomRepositoryImpl;
import com.saemodong.api.repository.activity.ActivityRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActivityService {

  private final ActivityRepository activityRepository;
  private final ActivityFieldMapper activityFieldMapper;
  private final ActivityCustomRepositoryImpl activityCustomRepositoryImpl;
  private final ActivityMapper activityMapper;

  public Pageable getPageable(Integer page, String sorter) {
    if (sorter.equals("latestAsc")) {
      return PageRequest.of(page, 10, Sort.by("createdAt").descending());
    } else {
      return PageRequest.of(page, 10, Sort.by("closedAt").ascending());
    }
  }

  private Page getPage(Pageable paging, String sorter, String isToday) {

    if (sorter.equals("latestAsc") && isToday.equals("N")) {
      // sorter: 최신 등록순, isToday: N
      return activityRepository.findAllByIsDeleted(paging, "N");

    } else if (sorter.equals("latestAsc") && isToday.equals("Y")) {
      // sorter: 최신 등록순, isToday: Y
      LocalDateTime startDate =
          LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(20, 0, 0, 0));
      LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59, 59));
      return activityRepository.findAllByIsDeletedAndCreatedAtBetween(
          paging, "N", startDate, endDate);
    } else if (sorter.equals("ddayAsc") && isToday.equals("N")) {
      // sorter: 마감일순, isToday: N
      LocalDateTime startDate = LocalDateTime.now();
      return activityRepository.findAllByIsDeletedAndClosedAtAfter(paging, "N", startDate);
    } else {
      // sorter: 마감일순, isToday: Y
      LocalDateTime startDate =
          LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(20, 0, 0));
      LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

      return activityRepository.findAllByIsDeletedAndCreatedAtBetween(
          paging, "N", startDate, endDate);
    }
  }

  public ActivityPageResponse getActivityList(
      Integer page, String sorter, String isToday, String apiKey) {

    Pageable paging = getPageable(page, sorter);
    Page<Activity> pageResult = getPage(paging, sorter, isToday);

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(pageResult.getContent(), apiKey);

    return ActivityPageResponse.toPageResponse(page, pageResult.getTotalPages(), result);
  }

  public ActivityPageResponse getActivityExtraList(
      Integer page,
      String sorter,
      String type,
      String field,
      String organizer,
      String district,
      String apiKey) {

    List<Long> typeId = new ArrayList<>();
    List<Long> fieldId = new ArrayList<>();
    List<Long> organizerId = new ArrayList<>();
    List<Long> districtId = new ArrayList<>();

    if (!type.isEmpty()) {
      typeId = splitByDivider(type);
    }
    if (!field.isEmpty()) {
      fieldId = splitByDivider(field);
    }
    if (!organizer.isEmpty()) {
      organizerId = splitByDivider(organizer);
    }
    if (!district.isEmpty()) {
      districtId = splitByDivider(district);
    }

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(
            activityCustomRepositoryImpl.findExtraBy(
                page, sorter, typeId, fieldId, districtId, organizerId),
            apiKey);

    return ActivityPageResponse.toPageResponse(
        page,
        activityCustomRepositoryImpl.getExtraTotalPage(
            sorter, typeId, fieldId, districtId, organizerId),
        result);
  }

  public ActivityPageResponse getActivityContestList(
      Integer page,
      String sorter,
      String type,
      String field,
      String organizer,
      String prize,
      String apiKey) {

    List<Long> typeId = new ArrayList<>();
    List<Long> fieldId = new ArrayList<>();
    List<Long> organizerId = new ArrayList<>();
    List<Long> prizeId = new ArrayList<>();

    if (!type.isEmpty()) {
      typeId = splitByDivider(type);
    }
    if (!field.isEmpty()) {
      fieldId = splitByDivider(field);
    }
    if (!organizer.isEmpty()) {
      organizerId = splitByDivider(organizer);
    }
    if (!prize.isEmpty()) {
      prizeId = splitByDivider(prize);
    }

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(
            activityCustomRepositoryImpl.findContestBy(
                page, sorter, typeId, fieldId, organizerId, prizeId),
            apiKey);

    return ActivityPageResponse.toPageResponse(
        page,
        activityCustomRepositoryImpl.getContestTotalPage(
            sorter, typeId, fieldId, organizerId, prizeId),
        result);
  }

  private List<Long> splitByDivider(String target) {

    return Arrays.asList(target.split("\\+")).stream()
        .map(Long::parseLong)
        .collect(Collectors.toList());
  }
}
