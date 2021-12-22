package com.saemodong.api.model.user;

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestActivityScreen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private LocalDateTime lastVisitedAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  private InterestActivityScreen(LocalDateTime lastVisitedAt, User user) {
    this.lastVisitedAt = lastVisitedAt;
    this.user = user;
  }

  public static InterestActivityScreen of(LocalDateTime lastVisitedAt, User user) {
    return new InterestActivityScreen(lastVisitedAt, user);
  }

  public void updateLastVisitedAt(LocalDateTime lastVisitedAt) {
    this.lastVisitedAt = lastVisitedAt;
  }
}
