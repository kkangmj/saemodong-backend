package com.saemodong.api.model.activity.contest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContestOrganizer {
  @Id
  private Long id;

  @Column
  private String organizer;
}
