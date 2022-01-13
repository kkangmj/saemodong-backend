package com.saemodong.api.service.user.interest;

import com.saemodong.api.common.DividerHelper;
import com.saemodong.api.model.user.User;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public abstract class ActivityCondition {

  protected final DividerHelper dividerHelper = new DividerHelper();

  @Transactional(readOnly = true)
  public abstract boolean existsByUser(User user);

  @Transactional
  public abstract void setCondition(User user, String condition);

  @Transactional(readOnly = true)
  public abstract List<Long> getCondition(User user);

  public String getConditionStr(User user) {
    List<Long> conditionList = getCondition(user);
    return dividerHelper.joinByDivider(conditionList);
  }
}
