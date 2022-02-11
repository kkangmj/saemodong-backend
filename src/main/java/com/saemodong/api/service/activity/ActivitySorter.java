package com.saemodong.api.service.activity;

import org.springframework.data.domain.Sort;

public enum ActivitySorter {
  LATEST_ASC("latestAsc") {
    public Sort getSort() {
      return Sort.by("createdAt").descending();
    }
  },
  DDAY_ASC("ddayAsc") {
    public Sort getSort() {
      return Sort.by("closedAt").ascending();
    }
  };

  public abstract Sort getSort();

  private final String sorter;

  ActivitySorter(String sorter) {
    this.sorter = sorter;
  }

  public static ActivitySorter fromSorter(String sorter) {
    for (ActivitySorter activitySorter: ActivitySorter.values()) {
      if (activitySorter.sorter.equalsIgnoreCase(sorter)){
        return activitySorter;
      }
    }
    // TODO 에러 던지기
    return null;
  }

  public String getSorterValue() {
    return sorter;
  }
}
