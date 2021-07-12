package com.cheerup.cheerup.service;

import com.cheerup.cheerup.dto.SayingRequestDto;
import com.cheerup.cheerup.model.Saying;
import com.cheerup.cheerup.repository.SayingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SayingService {

    private final SayingRepository SayingRepository;

    public Saying getSaying(Long num) {
        return SayingRepository.findByNum(num);
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Saying createSaying(SayingRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Saying saying = new Saying(requestDto);
        SayingRepository.save(saying);
        return saying;
    }
}