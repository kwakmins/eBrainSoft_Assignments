package com.ebsoft.ebstudytemplates4weekbackend.domain.board.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardWriteFormResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response.CategoryResDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final CategoryRepository categoryRepository;

  /**
   * 게시판 작성시 필요한 정보 전달.
   *
   * @return 게시판 작성시 필요한 정보
   */
  public BoardWriteFormResDto getBoardWriteForm() {
    List<CategoryResDto> categories = categoryRepository.findAll().stream()
        .map(CategoryResDto::new)
        .collect(Collectors.toList());

    return BoardWriteFormResDto.form(categories);
  }
}
