package com.saemodong.api.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(length=20, nullable = false)
  private String nickname;

  @Column(nullable = false)
  private Integer rpm;

  @Column(nullable = false)
  private String apiKey;
}
