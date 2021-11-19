package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.extra.ExtraDistrict;
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
public class UserExtraDistrict {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "extraDistrictId")
  private ExtraDistrict extraDistrict;

  private UserExtraDistrict(User user, ExtraDistrict extraDistrict) {
    this.user = user;
    this.extraDistrict = extraDistrict;
  }

  public static UserExtraDistrict of(User user, ExtraDistrict extraDistrict) {
    return new UserExtraDistrict(user, extraDistrict);
  }
}
