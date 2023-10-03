package com.ebsoft.ebstudytemplates3week.domain.file.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.file.convenience.FileStore;
import com.ebsoft.ebstudytemplates3week.domain.file.dao.FileRepository;
import com.ebsoft.ebstudytemplates3week.domain.file.dto.FileDto;
import com.ebsoft.ebstudytemplates3week.domain.file.dto.request.FileWriteDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FileService {

  private final FileRepository fileRepository;
  private final FileStore fileStore;

  @Transactional
  public void addFile(BoardWriteDto boardWriteDto) {
    try {
      List<FileDto> fileDtos = fileStore.storeFiles(boardWriteDto.getFiles());
      List<FileWriteDto> fileWriteDtos = new ArrayList<>();
      for (FileDto fileDto : fileDtos) {
        fileWriteDtos.add(new FileWriteDto(boardWriteDto.getBoardId(), fileDto));
        log.info(fileDto.toString());
      }
      fileRepository.saveFiles(fileWriteDtos);
    } catch (IOException e) {
      log.info("파일을 추가하는데 오류가 발생했습니다.");
      throw new IllegalArgumentException();
    }
  }
}
