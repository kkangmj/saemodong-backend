package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.contest.ContestOrganizer;
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
public class UserContestOrganizer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "contestOrganizerId")
  private ContestOrganizer contestOrganizer;

  private UserContestOrganizer(User user, ContestOrganizer contestOrganizer) {
    this.user = user;
    this.contestOrganizer = contestOrganizer;
  }

  public static UserContestOrganizer of(User user, ContestOrganizer contestOrganizer) {
    return new UserContestOrganizer(user, contestOrganizer);
  }
}
