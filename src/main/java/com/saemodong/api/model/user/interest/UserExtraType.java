package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.extra.ExtraType;
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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserExtraType extends UserInterestBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "extraTypeId")
  private ExtraType extraType;

  private UserExtraType(User user, ExtraType extraType) {
    this.user = user;
    this.extraType = extraType;
  }

  public static UserExtraType of(User user, ExtraType extraType) {
    return new UserExtraType(user, extraType);
  }

}
