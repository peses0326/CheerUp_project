package com.cheerup.cheerup.model;

import com.cheerup.cheerup.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String saying;

    @Transient
    private Long commentsCount;

    @Transient
    private Long likesCount;

    public void addCommentsCount(Long count) {
        this.commentsCount = count;}

    public void addLikesCount(Long count) {
        this.likesCount = count;}
        
    public Article(ArticleRequestDto requestDto, String username) {
        this.saying = requestDto.getSaying();
        this.username = username;
        this.content = requestDto.getContent();
    }

    public void update(ArticleRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}