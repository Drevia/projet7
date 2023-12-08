package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;

    private String name;
    private String description;
    private String json;
    private  String template;
    private String sqlStr;
    private String sqlPart;

    public RuleName(String ruleName, String description, String json, String template, String sqlStr, String sqlPart) {

    }

    public RuleName() {
    }

}
