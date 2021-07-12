package com.cheerup.cheerup.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Ip {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String ipAdress;

    public Ip(String ipAdress){
        this.ipAdress = ipAdress;
    }

    public Ip(Ip receivedIp){
        this.ipAdress = receivedIp.getIpAdress();
    }
}