package com.saemodong.api.model.activity.contest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ContestType {
  @Id private Long id;

  @Column private String type;
}
