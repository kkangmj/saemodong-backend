package com.saemodong.api.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PaginationResponseDto {
  private Integer currentPage;
  private Integer totalPage;
  private List<?> pageResult;

  public static PaginationResponseDto of(Integer currentPage, Integer totalPage, List<?> result) {

    return new PaginationResponseDto(currentPage, totalPage, result);
  }
}
