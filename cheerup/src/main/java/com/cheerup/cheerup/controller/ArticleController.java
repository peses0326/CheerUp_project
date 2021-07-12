package com.cheerup.cheerup.controller;

import com.cheerup.cheerup.dto.ArticleRequestDto;
import com.cheerup.cheerup.model.Article;
import com.cheerup.cheerup.repository.ArticleRepository;
import com.cheerup.cheerup.security.UserDetailsImpl;
import com.cheerup.cheerup.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/article")
    public List<Article> getArticle() {
        return articleService.updateCounter();
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }

    @PostMapping("/article")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID의 username
        String username = "";
        if (userDetails != null) {
            username = userDetails.getUser().getUsername();
        }
        return articleService.createArticle(requestDto, username);
    }

    @PutMapping("/article/{id}")
    public Long updateText(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        articleService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/article/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }
}
