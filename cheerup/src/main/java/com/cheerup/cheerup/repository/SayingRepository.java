package com.cheerup.cheerup.repository;

import com.cheerup.cheerup.model.Saying;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SayingRepository extends JpaRepository<Saying, Long> {
    //        Optional<Saying> optional = SayingRepository.findBynum(Long num)
    Saying findByNum(Long num);
}