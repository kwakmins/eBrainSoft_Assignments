package com.ebsoft.ebstudytemplates3week.domain.board.dto.request;

import com.ebsoft.ebstudytemplates3week.global.paging.Pagination;
import lombok.Data;

@Data
public class SearchDto {

  // 시작 날짜
  public String startDate;
  // 끝 날짜
  public String endDate;
  // 카테고리 아이디
  public Long category;
  // 검색어
  public String searchContent;
  // 페이지
  public Pagination pagination;

  public String toUrlParm() {
    String temp = "";
    if (startDate != null) {
      temp += "&startDate=" + startDate;
    }
    if (endDate != null) {
      temp += "&endDate=" + endDate;
    }
    if (category != null) {
      temp += "&category=" + category;
    }
    if (searchContent != null) {
      temp += "&searchContent=" + searchContent;
    }
    return temp;
  }
}
