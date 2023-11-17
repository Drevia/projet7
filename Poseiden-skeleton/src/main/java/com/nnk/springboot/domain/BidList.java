package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
public class BidList {
    @Id
    private Long bidListId;
    private String account;
    private String type;
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
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

    public BidList(String accountTest, String typeTest, double bid) {

    }

    public BidList() {

    }

    public void setBidListId(Long bidListId) {
        this.bidListId = bidListId;
    }

    public Long getBidListId() {
        return bidListId;
    }
    // TODO: Map columns in data table BIDLIST with corresponding java fields
}
