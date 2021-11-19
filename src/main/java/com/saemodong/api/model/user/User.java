package com.saemodong.api.model.user;

import com.saemodong.api.model.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false)
  private String nickname;

  @Column(length = 20, nullable = false)
  private String apiKey;

  //  @Column(length = 1, nullable = false)
  //  @ColumnDefault("'N'")
  //  private String setInterest;

  @Column
  @ColumnDefault("''")
  private String feedbackUrl;

  @Column(length = 1, nullable = false)
  @ColumnDefault("'N'")
  private String isDeleted;

  @Column private LocalDateTime deletedAt;

  private User(String nickname, String apiKey) {
    this.nickname = nickname;
    this.apiKey = apiKey;
  }

  public static User of(String nickname, String apiKey) {
    return new User(nickname, apiKey);
  }
}
