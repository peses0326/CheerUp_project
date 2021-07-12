package com.cheerup.cheerup.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long articleId;
    private String username;
    private String comment;
}