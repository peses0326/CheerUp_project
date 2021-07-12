package com.cheerup.cheerup.repository;

import com.cheerup.cheerup.model.Ip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IpRepository extends JpaRepository<Ip, Long> {
    Optional<Ip> findByIpAdress(String visitorIp);
//    List<Ip> findAllByOrderByTotalVisitorsDesc();
}