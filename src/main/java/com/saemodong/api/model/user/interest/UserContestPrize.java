package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.contest.ContestPrize;
import com.saemodong.api.model.user.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserContestPrize {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "contestPrizeId")
  private ContestPrize contestPrize;

  private UserContestPrize(User user, ContestPrize contestPrize) {
    this.user = user;
    this.contestPrize = contestPrize;
  }

  public static UserContestPrize of(User user, ContestPrize contestPrize) {
    return new UserContestPrize(user, contestPrize);
  }
}
