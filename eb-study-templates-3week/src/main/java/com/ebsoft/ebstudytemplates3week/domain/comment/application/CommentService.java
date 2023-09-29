package com.ebsoft.ebstudytemplates3week.domain.comment.application;

import com.ebsoft.ebstudytemplates3week.domain.comment.dao.CommentRepository;
import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService {

  private final CommentRepository commentRepository;

  @Transactional
  public void addComment(CommentDto commentDto) {
    commentRepository.writeComment(commentDto);
  }
}
