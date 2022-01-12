package com.saemodong.api.model.view;

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestActivityView {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private LocalDateTime lastVisitedAt;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  private InterestActivityView(User user, LocalDateTime lastVisitedAt) {
    this.lastVisitedAt = lastVisitedAt;
    this.user = user;
  }

  public void updateLastVisitedAt(LocalDateTime localDateTime) {
    this.lastVisitedAt = localDateTime;
  }

  public static InterestActivityView of(User user, LocalDateTime lastVisitedAt) {
    return new InterestActivityView(user, lastVisitedAt);
  }
}
