package com.cheerup.cheerup.service;

import com.cheerup.cheerup.dto.LikeItRequestDto;
import com.cheerup.cheerup.model.LikeIt;
import com.cheerup.cheerup.repository.LikeItRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeItService {

    private final LikeItRepository likeItRepository;

    @Transactional
    public String iLikeIt(LikeItRequestDto requestDto) {
        LikeIt likeIt = new LikeIt(requestDto);
        Optional<LikeIt> likeSize = Optional.ofNullable(likeItRepository.findByUsernameAndArticleId(requestDto.getUsername(), requestDto.getArticleId()));
        if (likeSize.isPresent()) { // 이미 좋아요를 누른 상태
            likeItRepository.deleteByUsernameAndArticleId(requestDto.getUsername(), requestDto.getArticleId());
            return requestDto.getArticleId() + " likeIt cancelled!";
        } else { // 좋아요를 누르지 않은 상태
            likeItRepository.save(likeIt);
            return requestDto.getArticleId() + " likeIt!";
        }
    }
}
