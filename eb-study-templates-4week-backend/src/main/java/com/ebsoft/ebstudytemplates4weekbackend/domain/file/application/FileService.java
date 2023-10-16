package com.ebsoft.ebstudytemplates4weekbackend.domain.file.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.file.dao.FileRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.file.entity.File;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.BusinessException;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.ErrorCode;
import com.ebsoft.ebstudytemplates4weekbackend.global.util.file.FileStore;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

  public static final int FILE_MAX_SIZE = 3;

  private final FileRepository fileRepository;
  private final FileStore fileStore;

  /**
   * 파일 생성
   *
   * @param board          관련된 게시판
   * @param multipartFiles 파일 리스트
   */
  @Transactional
  public void createFile(Board board, List<MultipartFile> multipartFiles) {
    validFileMaxSize(multipartFiles.size());

    try {
      List<File> files = fileStore.storeFiles(multipartFiles).stream()
          .map(fileDto -> fileDto.toEntity(board))
          .collect(Collectors.toList());
      
      fileRepository.saveAll(files);
    } catch (IOException e) {
      throw new BusinessException(null, "file", ErrorCode.FILE_IO_EXCEPTION);
    }
  }

  /**
   * 파일이 3개이상이면 예외 발상
   *
   * @param size 파일 리스트의 사이즈
   */
  private void validFileMaxSize(Integer size) {
    if (size > FILE_MAX_SIZE) {
      throw new BusinessException(size, "fileSize", ErrorCode.FILE_MAX_SIZE);
    }
  }

}
