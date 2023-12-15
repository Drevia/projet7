package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "trade")
@Getter
@Setter
@EqualsAndHashCode
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;

    private String account;
    private String type;
    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;
    private String benchmark;
    private Timestamp tradeDate;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Timestamp creationDate;
    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Trade(String tradeAccount, String type) {

    }

    public Trade() {

    }
}
