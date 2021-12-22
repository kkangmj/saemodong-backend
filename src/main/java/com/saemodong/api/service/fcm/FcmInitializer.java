package com.saemodong.api.service.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmInitializer {

  @Value("${app.firebase-configuration-file}")
  private String firebaseConfigPath;

  @PostConstruct
  public void initialize() {
    try {
      FirebaseOptions options =
          new FirebaseOptions.Builder()
              .setCredentials(
                  GoogleCredentials.fromStream(
                      new ClassPathResource(firebaseConfigPath).getInputStream()))
              .build();
      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
      }
    } catch (IOException e) {
      // TODO 로깅 & 에러 핸들링
    }
  }
}
