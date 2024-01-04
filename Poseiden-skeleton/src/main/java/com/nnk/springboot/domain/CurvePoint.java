package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer curveId;
    private Timestamp asOfDate;
    @NotNull
    private Double term;

    @Column(name = "curve_value")
    @NotNull
    private Double value;
    private Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {

    }

    public CurvePoint() {

    }
}
