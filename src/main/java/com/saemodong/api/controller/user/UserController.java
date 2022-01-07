package com.saemodong.api.controller.user;

import com.saemodong.api.common.KeyHelper;
import com.saemodong.api.dto.ApiResponse;
import com.saemodong.api.dto.FailureResponse;
import com.saemodong.api.dto.ResultCode;
import com.saemodong.api.dto.SuccessResponse;
import com.saemodong.api.dto.user.ContestInterestDto;
import com.saemodong.api.dto.user.ExtraInterestDto;
import com.saemodong.api.dto.user.UserLoginResponseDto;
import com.saemodong.api.dto.user.UserRequestDto;
import com.saemodong.api.dto.user.UserRegisterResponseDto;
import com.saemodong.api.model.user.User;
import com.saemodong.api.repository.user.UserRepository;
import com.saemodong.api.service.user.UserInterestService;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserRepository userRepository;
  private UserInterestService userInterestService;
  private KeyHelper keyHelper;

  @PostMapping("register")
  public ResponseEntity<? extends ApiResponse> register(
      @RequestBody @Valid UserRequestDto userRequestDto) {
    String nickname = userRequestDto.getNickname().trim();

    if (userRepository.existsByNickname(nickname)) {
      return new ResponseEntity<>(
          FailureResponse.of(ResultCode.BAD_REQUEST, "이미 존재하는 닉네임입니다."), HttpStatus.BAD_REQUEST);
    }

    String apiKey = keyHelper.getApiKey();
    userRepository.save(User.of(nickname, apiKey));

    return ResponseEntity.ok(SuccessResponse.of(UserRegisterResponseDto.of(nickname, apiKey, "N")));
  }

  @GetMapping("login")
  public ResponseEntity<? extends ApiResponse> login(@RequestParam String apiKey) {

    Optional<User> user = userRepository.findByApiKey(apiKey);

    if (user.isPresent()) {
      return ResponseEntity.ok(
          SuccessResponse.of(
              UserLoginResponseDto.of(user.get().getNickname(), user.get().getSetInterest())));
    } else {
      return new ResponseEntity(
          FailureResponse.of(ResultCode.NOT_FOUND, "사용자가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("interest/extra")
  public ResponseEntity<? extends ApiResponse> getExtraInterest(@RequestParam String apiKey) {

    Optional<User> user = userRepository.findByApiKey(apiKey);

    if (user.isPresent()) {
      ExtraInterestDto extraInterestDto = userInterestService.getUserExtraInterest(user.get());
      return ResponseEntity.ok(SuccessResponse.of(extraInterestDto));
    } else {
      return new ResponseEntity(
          FailureResponse.of(ResultCode.NOT_FOUND, "사용자가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("interest/contest")
  public ResponseEntity<? extends ApiResponse> getContestInterest(@RequestParam String apiKey) {

    if (!keyHelper.validate(apiKey)) {
      return new ResponseEntity(
          FailureResponse.of(ResultCode.NOT_FOUND, "사용자가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    } else {
      Optional<User> user = userRepository.findByApiKey(apiKey);
      ContestInterestDto contestInterestDto =
          userInterestService.getUserContestInterest(user.get());
      return ResponseEntity.ok(SuccessResponse.of(contestInterestDto));
    }
  }

  @PutMapping("interest/extra/{apiKey}")
  public ResponseEntity<? extends ApiResponse> setExtraInterest(
      @PathVariable String apiKey, @RequestBody @Valid ExtraInterestDto extraInterestDto) {

    if (!keyHelper.validate(apiKey)) {
      return new ResponseEntity(
          FailureResponse.of(ResultCode.NOT_FOUND, "사용자가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    } else {

      userInterestService.setUserExtraInterest(
          apiKey,
          extraInterestDto.getType(),
          extraInterestDto.getField(),
          extraInterestDto.getOrganizer(),
          extraInterestDto.getDistrict());

      return ResponseEntity.noContent().build();
    }
  }

  @PutMapping("interest/contest/{apiKey}")
  public ResponseEntity<? extends ApiResponse> setContestInterest(
      @PathVariable String apiKey, @RequestBody @Valid ContestInterestDto contestInterestDto) {

    if (!keyHelper.validate(apiKey)) {
      return new ResponseEntity(
          FailureResponse.of(ResultCode.NOT_FOUND, "사용자가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    } else {
      userInterestService.setUserContestInterest(
          apiKey,
          contestInterestDto.getType(),
          contestInterestDto.getField(),
          contestInterestDto.getOrganizer(),
          contestInterestDto.getPrize());

      return ResponseEntity.noContent().build();
    }
  }
}
