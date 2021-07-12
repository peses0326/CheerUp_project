package com.cheerup.cheerup.dto;

import lombok.Getter;

@Getter
public class LikeItRequestDto {
    private String username;
    private Long articleId;
}