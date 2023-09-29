package com.ebsoft.ebstudytemplates3week.domain.comment.dao;

import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {

  void writeComment(CommentDto commentDto);
}
