package com.saemodong.api.service.user.interest;

import com.saemodong.api.common.DividerHelper;
import com.saemodong.api.model.user.User;
import java.util.List;

public abstract class ActivityCondition {

  protected final DividerHelper dividerHelper = new DividerHelper();

  public abstract boolean existsByUser(User user);

  public abstract void setCondition(User user, String condition);

  public abstract List<Long> getCondition(User user);

  public String getConditionStr(User user) {
    List<Long> conditionList = getCondition(user);
    return dividerHelper.joinByDivider(conditionList);
  }
}
