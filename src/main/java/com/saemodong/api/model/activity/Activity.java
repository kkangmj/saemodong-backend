package com.saemodong.api.model.activity;

import com.saemodong.api.model.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor
public class Activity extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer type;

  @Column private String url;

  @Column(nullable = false)
  private LocalDateTime openedAt;

  @Column(nullable = false)
  private LocalDateTime closedAt;

  @Column(length = 1, nullable = false)
  @ColumnDefault("'N'")
  private String isDeleted;

  @Column private LocalDateTime deletedAt;
}
