package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
