package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Min(0)
    private Integer curveId;
    private Timestamp asOfDate;
    @NotNull
    @Min(0)
    private Double term;

    @Column(name = "curve_value")
    @NotNull
    @Min(0)
    private Double value;
    private Timestamp creationDate;

}
