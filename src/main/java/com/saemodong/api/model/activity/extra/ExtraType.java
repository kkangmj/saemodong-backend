package com.saemodong.api.model.activity.extra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExtraType {
  @Id
  private Long id;

  @Column
  private String type;
}
