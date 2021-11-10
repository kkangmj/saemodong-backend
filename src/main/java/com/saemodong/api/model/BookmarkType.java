package com.saemodong.api.model;

public enum BookmarkType {
  ACTIVITY("activity"),
  TEAM("team");

  private final String type;

  BookmarkType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
