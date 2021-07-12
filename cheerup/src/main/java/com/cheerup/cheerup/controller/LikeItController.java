package com.cheerup.cheerup.controller;

import com.cheerup.cheerup.dto.LikeItRequestDto;
import com.cheerup.cheerup.model.LikeIt;
import com.cheerup.cheerup.repository.LikeItRepository;
import com.cheerup.cheerup.service.LikeItService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikeItController {

    private final LikeItRepository likeItRepository; // CRUD하려면 DB 필요
    private final LikeItService likeItService; // 업데이트용

    @GetMapping("/likeIt") // 좋아요 전체 조회
    public List<LikeIt> readLikes() {
        return likeItRepository.findAll();
    }

    @GetMapping("/likeIt/{articleId}") // 게시글 ID 별로 조회
    public List<LikeIt> readLikesById(@PathVariable Long articleId) {
        return likeItRepository.findAllByArticleId(articleId);
    }

    @GetMapping("/likeItCounter") // 좋아요 총갯수 조회
    public Long readLikesCounter() {
        return (long) (likeItRepository.findAll()).size();
    }

    @PostMapping("/likeIt") // 좋아요!
    public String clickLike(@RequestBody LikeItRequestDto requestDto) {
        return likeItService.iLikeIt(requestDto);
    }
}
