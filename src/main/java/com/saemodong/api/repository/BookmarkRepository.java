package com.saemodong.api.repository;

import com.saemodong.api.model.Bookmark;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

  Optional<Bookmark> findByTypeAndContentIdAndUserApiKey(
      String type, Long contentId, String apiKey);

  Page<Bookmark> findAllByTypeAndUserApiKey(Pageable pageable, String type, String apiKey);
}
