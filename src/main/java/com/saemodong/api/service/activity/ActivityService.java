package com.saemodong.api.service.activity;

import com.saemodong.api.common.DividerHelper;
import com.saemodong.api.dto.PaginationResponseDto;
import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.mapper.ActivityMapper;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.repository.activity.ActivityCustomRepository;
import com.saemodong.api.repository.activity.ActivityCustomRepositoryImpl;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class ActivityService {

  private final ActivityCustomRepository activityCustomRepository;
  private final ActivityCustomRepositoryImpl activityCustomRepositoryImpl;
  private final ActivityMapper activityMapper;
  private final DividerHelper dividerHelper;

  public PaginationResponseDto getActivityWithPageNumber(
      Integer pageNumber, String sorter, String registeredToday, String apiKey) {

    Page<Activity> activityInPage =
        activityCustomRepository.getActivityBy(pageNumber, sorter, registeredToday);

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(activityInPage.getContent(), apiKey);

    return PaginationResponseDto.of(pageNumber, activityInPage.getTotalPages(), result);
  }

  public PaginationResponseDto getActivityExtraList(
      Integer page,
      String sorter,
      String type,
      String field,
      String organizer,
      String district,
      String apiKey) {

    List<Long> typeId = dividerHelper.splitByDivider(type);
    List<Long> fieldId = dividerHelper.splitByDivider(field);
    List<Long> organizerId = dividerHelper.splitByDivider(organizer);
    List<Long> districtId = dividerHelper.splitByDivider(district);

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(
            activityCustomRepositoryImpl.findExtraBy(
                page, sorter, typeId, fieldId, districtId, organizerId),
            apiKey);

    return PaginationResponseDto.of(
        page,
        activityCustomRepositoryImpl.getExtraTotalPage(
            sorter, typeId, fieldId, districtId, organizerId),
        result);
  }

  public PaginationResponseDto getActivityContestList(
      Integer page,
      String sorter,
      String type,
      String field,
      String organizer,
      String prize,
      String apiKey) {

    List<Long> typeId = dividerHelper.splitByDivider(type);
    List<Long> fieldId = dividerHelper.splitByDivider(field);
    List<Long> organizerId = dividerHelper.splitByDivider(organizer);
    List<Long> prizeId = dividerHelper.splitByDivider(prize);

    List<ActivityResponseDto> result =
        activityMapper.toActivityResponseDto(
            activityCustomRepositoryImpl.findContestBy(
                page, sorter, typeId, fieldId, organizerId, prizeId),
            apiKey);

    return PaginationResponseDto.of(
        page,
        activityCustomRepositoryImpl.getContestTotalPage(
            sorter, typeId, fieldId, organizerId, prizeId),
        result);
  }
}
