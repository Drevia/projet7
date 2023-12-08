package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@EqualsAndHashCode
@Getter
@Setter
public class CurvePoint {
    @Id
    private Long id;

    private Integer curveId;
    private Timestamp asOfDate;
    private Double term;

    @Column(name = "curve_value")
    private Double value;
    private Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {

    }

    public CurvePoint() {

    }
}
