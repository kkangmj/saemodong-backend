package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.contest.ContestType;
import com.saemodong.api.model.user.User;
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
public class UserContestType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "contestTypeId")
  private ContestType contestType;

  private UserContestType(User user, ContestType contestType) {
    this.user = user;
    this.contestType = contestType;
  }

  public static UserContestType of(User user, ContestType contestType) {
    return new UserContestType(user, contestType);
  }
}
