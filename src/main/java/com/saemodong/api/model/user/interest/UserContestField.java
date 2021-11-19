package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.contest.ContestField;
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
public class UserContestField {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "contestFieldId")
  private ContestField contestField;

  private UserContestField(User user, ContestField contestField) {
    this.user = user;
    this.contestField = contestField;
  }

  public static UserContestField of(User user, ContestField contestField) {
    return new UserContestField(user, contestField);
  }
}
