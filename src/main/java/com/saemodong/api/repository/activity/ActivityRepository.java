package com.saemodong.api.repository.activity;

import com.saemodong.api.model.activity.Activity;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActivityRepository extends JpaRepository<Activity, Long> {

  Page<Activity> findAllByIsDeleted(Pageable pageable, String isDeleted);

  Page<Activity> findAllByIsDeletedAndRegisteredAtBetween(
      Pageable pageable, String isDeleted, LocalDateTime startDate, LocalDateTime endDate);

  Page<Activity> findAllByIsDeletedAndClosedAtAfter(
      Pageable pageable, String isDeleted, LocalDateTime yesDate);

  // findByIsDeletedAndRegisteredAtAnd
  // findByIsDeletedAndRegisteredAt
  // findByIsDeletedAndClosedAt
}
