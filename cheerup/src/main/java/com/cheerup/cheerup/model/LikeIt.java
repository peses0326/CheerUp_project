package com.cheerup.cheerup.model;

import com.cheerup.cheerup.dto.LikeItRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class LikeIt {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long articleId;

    public LikeIt(LikeItRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.articleId = requestDto.getArticleId();
    }
}