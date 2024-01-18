package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "rulename")
@EqualsAndHashCode
@Getter
@Setter
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String json;
    @NotNull
    @NotBlank
    private  String template;
    @NotNull
    @NotBlank
    private String sqlStr;
    @NotNull
    @NotBlank
    private String sqlPart;

}
