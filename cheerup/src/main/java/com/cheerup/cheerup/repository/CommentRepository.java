package com.cheerup.cheerup.repository;

import com.cheerup.cheerup.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc(); // 찾아줘 모두 정렬해줘 수정한 기준으로 내림차순
    List<Comment> findAllByArticleIdOrderByModifiedAtDesc(Long id);
    Long countByArticleId(Long textId);
}