package com.cheerup.cheerup.model;

import com.cheerup.cheerup.dto.SayingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Saying{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private Long num;

    @Column(nullable = false)
    private String saying;

    public Saying(Long num, String saying) {
        this.num = num;
        this.saying = saying;
    }

    public Saying(SayingRequestDto requestDto){
        this.saying = requestDto.getSaying();
        this.num = requestDto.getNum();
    }
}
