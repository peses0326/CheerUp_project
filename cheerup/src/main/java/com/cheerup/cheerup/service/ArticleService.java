package com.cheerup.cheerup.service;

import com.cheerup.cheerup.dto.ArticleRequestDto;
import com.cheerup.cheerup.model.Article;
import com.cheerup.cheerup.repository.ArticleRepository;
import com.cheerup.cheerup.repository.CommentRepository;
import com.cheerup.cheerup.repository.LikeItRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final LikeItRepository likeItRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Article createArticle(ArticleRequestDto requestDto, String username) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Article article = new Article(requestDto, username);
        articleRepository.save(article);
        return article;
    }

    @Transactional
    public Long update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article.getId();
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(" "));
    }

    public List<Article> updateCounter() {
        List<Article> articleList = articleRepository.findAllByOrderByCreatedAtDesc();
        return (likesCounter(commentsCounter(articleList)));
    }

    public List<Article> commentsCounter(List<Article> articleList) {
        for (Article value : articleList) {
            Long articleId = value.getId();
            Long commentsCount = commentRepository.countByArticleId(articleId);
            value.addCommentsCount(commentsCount);
        }
        return articleList;
    }

    public List<Article> likesCounter(List<Article> articleList) {
        for (Article value : articleList) {
            Long articleId = value.getId();
            Long likesCount = likeItRepository.countByArticleId(articleId);
            value.addLikesCount(likesCount);
        }
        return articleList;
    }

}
