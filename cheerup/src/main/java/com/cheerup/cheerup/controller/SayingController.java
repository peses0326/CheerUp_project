package com.cheerup.cheerup.controller;

import com.cheerup.cheerup.dto.SayingRequestDto;
import com.cheerup.cheerup.model.Saying;
import com.cheerup.cheerup.repository.SayingRepository;
import com.cheerup.cheerup.service.SayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
public class SayingController {

    private final SayingService SayingService;

    private final SayingRepository SayingRepository;

    @GetMapping("/saying")
    public Saying getSaying() {
        List<Saying> sayingList = SayingRepository.findAll();
        Random rand = new Random();
        long num = rand.nextInt(sayingList.size());
        return SayingService.getSaying(num);
    }

    @PostMapping("/saying")
    public Saying createSaying(@RequestBody SayingRequestDto requestDto) {
        return SayingService.createSaying(requestDto);
    }
}
