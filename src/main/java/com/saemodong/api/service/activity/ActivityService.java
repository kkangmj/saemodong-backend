package com.saemodong.api.service.activity;

import com.saemodong.api.repository.activity.ActivityRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

  public ActivityPageResponse getActivityList(Integer page, String sorter, String isToday) {

    Pageable paging = getPageable(page, sorter);
    Page pageResult = getPage(paging, sorter, isToday);

    return ActivityPageResponse.toPageResponse(
        page, pageResult.getTotalPages(), pageResult.getContent());
  }
}
