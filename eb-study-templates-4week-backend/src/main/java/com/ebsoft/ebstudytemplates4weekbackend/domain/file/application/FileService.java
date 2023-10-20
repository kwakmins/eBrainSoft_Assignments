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
   * todo 코드 재활용성으로 이전 코드 지우고, 이번 코드로 합칠 수 있을 것 같지만, {?}service에서 {?}을 저장하는게 좋지 않을까?
   *
   * 멀티파트 파일 리스트를 엔티티 리스트로 변경
   *
   * @param board          게시판
   * @param multipartFiles 멀티파트 파일
   * @return 파일 엔티티 리스트
   */
  public List<File> multipartToEntity(Board board, List<MultipartFile> multipartFiles) {
    validFileMaxSize(multipartFiles.size());

    try {
      List<File> files = fileStore.storeFiles(multipartFiles).stream()
          .map(fileDto -> fileDto.toEntity(board))
          .collect(Collectors.toList());

      return files;
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

  // todo 클라이언트에 리소스를 주지말고, 클라이언트 서버에서 받은 정보를 통해, 리소스를 가져오는게 맞다고 생각함.
//  /**
//   * 파일 Entity List를 리소스화
//   *
//   * @param files 파일 Entity 리스트
//   * @return 리소스 리스트
//   */
//  public List<Resource> getFileResourceList(List<File> files) {
//    return files.stream()
//        .map(this::getFileResource)
//        .collect(Collectors.toList());
//  }
//
//  /**
//   * 파일 Entity를 리소스화
//   *
//   * @param file 파일 Entity
//   * @return 파일 리소스
//   */
//  private Resource getFileResource(File file) {
//    try {
//      Path path = Paths.get(fileStore.getFullPath(file.getFileName()));
//
//      return new InputStreamResource(Files.newInputStream(path));
//    } catch (IOException e) {
//      throw new BusinessException(file.getId(), "fileId", ErrorCode.FILE_IO_EXCEPTION);
//    }
//  }

}
