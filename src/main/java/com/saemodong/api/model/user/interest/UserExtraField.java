package com.saemodong.api.model.user.interest;

import com.saemodong.api.model.activity.extra.ExtraField;
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
public class UserExtraField {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "extraFieldId")
  private ExtraField extraField;

  private UserExtraField(User user, ExtraField extraField) {
    this.user = user;
    this.extraField = extraField;
  }

  public static UserExtraField of(User user, ExtraField extraField) {
    return new UserExtraField(user, extraField);
  }
}
