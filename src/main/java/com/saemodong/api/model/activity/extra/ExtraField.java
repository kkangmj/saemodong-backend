package com.saemodong.api.model.activity.extra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ExtraField {
  @Id
  private Long id;

  @Column
  private String field;
}
