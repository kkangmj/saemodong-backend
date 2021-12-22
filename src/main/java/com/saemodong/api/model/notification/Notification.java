package com.saemodong.api.model.notification;

import com.saemodong.api.model.BaseTimeEntity;
import com.saemodong.api.model.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 1, nullable = false)
  @ColumnDefault("'N'")
  private String isRead;

  @Column private LocalDateTime readAt;

  @Column(length = 1, nullable = false)
  @ColumnDefault("'N'")
  private String isSent;

  @Column private LocalDateTime sentAt;

  @Column private Integer type;

  @Column private String title;

  @Column private String content;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
}
