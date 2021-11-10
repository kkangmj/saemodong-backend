package com.saemodong.api.service;

import com.saemodong.api.dto.activity.ActivityResponseDto;
import com.saemodong.api.mapper.ActivityFieldMapper;
import com.saemodong.api.model.Bookmark;
import com.saemodong.api.model.activity.Activity;
import com.saemodong.api.model.user.User;
import com.saemodong.api.repository.BookmarkRepository;
import com.saemodong.api.repository.activity.ActivityRepository;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.service.activity.ActivityPageResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkService {

  private final UserRepository userRepository;
  private final BookmarkRepository bookmarkRepository;
  private final ActivityRepository activityRepository;
  private final ActivityFieldMapper activityFieldMapper;

  public void mark(String type, Long activityId, String apiKey) {

    Optional<User> user = userRepository.findByApiKey(apiKey);
    Optional<Bookmark> bookmark =
        bookmarkRepository.findByTypeAndContentIdAndUserApiKey(type, activityId, apiKey);

    if (user.isPresent() && !bookmark.isPresent()) {
      bookmarkRepository.save(Bookmark.of(type, activityId, user.get()));
    }
  }

  public void unmark(String type, Long activityId, String apiKey) {

    Optional<Bookmark> bookmark =
        bookmarkRepository.findByTypeAndContentIdAndUserApiKey(type, activityId, apiKey);

    if (bookmark.isPresent()) {
      bookmarkRepository.delete(bookmark.get());
    }
  }

  public ActivityPageResponse getBookmark(Integer page, String type, String apiKey) {

    Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());

    Page<Bookmark> pageResult =
        bookmarkRepository.findAllByTypeAndUserApiKey(pageable, type, apiKey);

    List<ActivityResponseDto> result =
        pageResult.getContent().stream()
            .map(
                bookmark -> {
                  Optional<Activity> activity =
                      activityRepository.findById(bookmark.getContentId());
                  if (activity.isPresent()) {
                    return ActivityResponseDto.of(
                        activity.get(),
                        activityFieldMapper.getActivityField(
                            activity.get().getId(), activity.get().getType()),
                        true);
                  }
                  return null;
                })
            .collect(Collectors.toList());

    return ActivityPageResponse.toPageResponse(page, pageResult.getTotalPages(), result);
  }
}
