package com.ebsoft.ebstudytemplates4weekbackend.domain.board.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardWriteReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardDetailResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardListResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardResDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  /**
   * 게시판 생성
   *
   * @param reqDto         게시판 생성시 필요한 reqDto
   * @param multipartFiles 게시판 첨부파일들
   * @return 생성된 게시판 id
   */
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

  /**
   * 게시판 id로 상세보기
   *
   * @param boardId 찾을 게시판 id
   * @return 상세보기 게시판 resDto
   */
  public BoardDetailResDto getBoardDetail(Long boardId) {
    Board board = getBoardById(boardId);

    return new BoardDetailResDto(board);
  }

  /**
   * 게시판 id로 게시판 찾기. 없으면 예외처리
   *
   * @param boardId 찾을 게시판 id
   * @return 찾은 게시판 Entity
   */
  private Board getBoardById(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(
        () -> new BusinessException(boardId, "boardId", ErrorCode.BOARD_NOT_FOUND)
    );
  }

  /**
   * 게시판 검색 목록 조회
   *
   * @param pageable   페이지에 관한 정보
   * @param categoryId 검색할 카테고리 id
   * @param search     검색어
   * @return 게시판 목록 정보 및 페이지에 관한 정보
   */
  public BoardListResDto getBoards(Pageable pageable, Long categoryId, String search) {
    Page<Board> boards;

    // 카테고리 유무에 따른, query문 변경
    if (categoryId == null) {
      boards = boardRepository.findBoardsByUserNameContainingOrContentContainingOrTitleContaining(
          search, search, search, pageable);
    } else {
      boards = boardRepository.findSearchWithCategoryBoards(
          getCategoryById(categoryId), search, pageable);
    }

    return new BoardListResDto(boards.get()
        .map(BoardResDto::new)
        .collect(Collectors.toList()),
        boards.getTotalElements(),
        boards.getTotalPages(),
        boards.getPageable().getPageNumber());
  }

  /**
   * 비밀번호 매치 확인
   *
   * @param boardId  게시판 id
   * @param password 비밀번호
   */
  public void checkPassword(Long boardId, String password) {
    boolean isMatchPassword = passwordEncoder.matches(password,
        getBoardById(boardId).getPassword());

    if (!isMatchPassword) {
      throw new BusinessException(boardId, "boardId", ErrorCode.BOARD_WRONG_PASSWORD_CONFIRM);
    }
  }
}
