package com.ebsoft.ebstudytemplates3week.domain.file.dao;

import com.ebsoft.ebstudytemplates3week.domain.file.dto.FileDto;
import com.ebsoft.ebstudytemplates3week.domain.file.dto.request.FileWriteDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository {

  void saveFiles(List<FileWriteDto> fileWriteDtos);

  FileDto findFileById(Long fileId);
}
