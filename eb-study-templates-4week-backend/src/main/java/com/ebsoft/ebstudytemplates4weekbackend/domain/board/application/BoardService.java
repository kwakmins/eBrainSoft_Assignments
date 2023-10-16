package com.ebsoft.ebstudytemplates4weekbackend.domain.board.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardWriteReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardWriteFormResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response.CategoryResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import com.ebsoft.ebstudytemplates4weekbackend.domain.file.application.FileService;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.BusinessException;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final CategoryRepository categoryRepository;
  private final PasswordEncoder passwordEncoder;

  private final FileService fileService;

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

  @Transactional
  public Long createBoard(BoardWriteReqDto reqDto, List<MultipartFile> multipartFiles) {
    //비밀번호 일치 확인
    isWrongPasswordConfirm(reqDto);

    //TODO 인라인화 vs 인라인화 X + 줄바꿈
    //카테고리 찾기
    Category category = getCategoryById(reqDto.getCategoryId());
    //비밀번호 인코딩
    String encodedPassword = passwordEncoder.encode(reqDto.getPassword());
    // Entity 생성
    Board board = reqDto.toEntity(category, encodedPassword);

    // 게시판 저장 후, id 얻기
    Long createdBoardId = boardRepository.save(board).getId(); //board에 자동으로 id가 주입됨
    // 파일 저장 (파일 저장 후 게시판을 저장하면, boardId=null + file updateQuery 나감)
    if (multipartFiles != null) {
      fileService.createFile(board, multipartFiles); //boardId != null
    }

    return createdBoardId;
  }

  /**
   * 비밀번호가 서로 일치하는지 확인. 다르면 예외처리.
   *
   * @param reqDto 게시판 작성 reqDto
   */
  private void isWrongPasswordConfirm(BoardWriteReqDto reqDto) {
    if (!reqDto.isRightPasswordConfirm()) {
      throw new BusinessException(null, "passwordConfirm", ErrorCode.BOARD_WRONG_PASSWORD_CONFIRM);
    }
  }

  /**
   * 카테고리 아이디로 카테고리 찾기. 없으면 예외처리.
   *
   * @param categoryId 찾을 카테고리 아이디
   * @return 찾은 카테고리
   */
  private Category getCategoryById(Long categoryId) {
    return categoryRepository.findById(categoryId).orElseThrow(
        () -> new BusinessException(categoryId, "categoryId",
            ErrorCode.CATEGORY_NOT_FOUND)
    );
  }
}
