package com.cheerup.cheerup.service;

import com.cheerup.cheerup.model.Ip;
import com.cheerup.cheerup.repository.IpRepository;
//import com.cheerup.cheerup.repository.VisitorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class IpService {
    private final IpRepository ipRepository;

//    private final VisitorsRepository visitorsRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void createIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String visitorIp = req.getHeader("X-FORWARDED-FOR");
        visitorIp = req.getRemoteAddr();
        Optional<Ip> foundIp = ipRepository.findByIpAdress(visitorIp);
        if (!foundIp.isPresent()) {
            Ip ip = new Ip(visitorIp);
            ipRepository.save(ip);
//            Long tempTotalVisitors = visitorsRepository.findAll().get(0).getTotalVisitors();
//            visitorsRepository.updateTotalVisitors(tempTotalVisitors+1, );
        }else{
            System.out.println("이미 있는 IP입니다.");
            System.out.println("RuntimeException");
        }
//        else {
//            throw new RuntimeException("이미 접속한 유저입니다.");
//        }
    }
}