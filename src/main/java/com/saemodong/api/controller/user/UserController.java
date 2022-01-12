package com.saemodong.api.controller.user;

import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.dto.user.UserLoginDto;
import com.saemodong.api.dto.user.UserRegisterDto;
import com.saemodong.api.dto.user.UserRequestDto;
import com.saemodong.api.service.user.UserService;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

  private UserService userService;

  @PostMapping("register")
  public ResponseEntity<? extends ApiResponse> register(
      @RequestBody @Valid UserRequestDto userRequestDto) {

    String nickname = userRequestDto.getNickname().trim();
    UserRegisterDto userRegisterDto = userService.registerByNickname(nickname);

    return ResponseEntity.ok(SuccessResponse.of(userRegisterDto));
  }

  @GetMapping("login")
  public ResponseEntity<? extends ApiResponse> login(@RequestParam String apiKey) {

    UserLoginDto userLoginDto = userService.loginByApiKey(apiKey);

    return ResponseEntity.ok(SuccessResponse.of(userLoginDto));
  }
}
