package com.saemodong.api.service.view;

import java.time.LocalDateTime;

public interface ViewService {
  String getLastVisitedAt(String apiKey);

  void setLastVisitedAt(String apiKey, LocalDateTime localDateTime);
}
