package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.extra.ExtraOrganizer;
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
public class UserExtraOrganizer extends UserInterestBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "extraOrganizerId")
  private ExtraOrganizer extraOrganizer;

  private UserExtraOrganizer(User user, ExtraOrganizer extraOrganizer) {
    this.user = user;
    this.extraOrganizer = extraOrganizer;
  }

  public static UserExtraOrganizer of(User user, ExtraOrganizer extraOrganizer) {
    return new UserExtraOrganizer(user, extraOrganizer);
  }
}
