package com.ebsoft.ebstudytemplates4weekbackend.global.util.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

  @Value("${file.dir}")
  private String fileDir;

  public String getFullPath(String filename) {
    return fileDir + filename;
  }

  // 멀티파트 파일리스트를 fileDto 형태 리스트로 변환.
  public List<FileDto> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
    List<FileDto> storeFileResult = new ArrayList<>();
    for (MultipartFile multipartFile : multipartFiles) {
      if (!multipartFile.isEmpty()) {
        storeFileResult.add(storeFile(multipartFile));
      }
    }
    return storeFileResult;
  }

  // 파일 이름 변환 및 멀티파트 파일 -> fileDto로 변환
  public FileDto storeFile(MultipartFile multipartFile) throws IOException {
    if (multipartFile.isEmpty()) {
      return null;
    }
    String originalFilename = multipartFile.getOriginalFilename();
    String storeFileName = createStoreFileName(originalFilename);

    multipartFile.transferTo(new File(getFullPath(storeFileName))); // application.yml에 지정된 경로에 저장

    return new FileDto(originalFilename, storeFileName);
  }

  // 고유 이름 생성
  private String createStoreFileName(String originalFilename) {
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID().toString();
    return uuid + "." + ext;
  }

  // 확장자명 추출
  private String extractExt(String originalFilename) {
    int pos = originalFilename.lastIndexOf(".");
    return originalFilename.substring(pos + 1);
  }
}