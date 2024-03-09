package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rating")
@EqualsAndHashCode
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String moodysRating;

    @NotNull
    @NotBlank
    private String sandPRating;

    @NotNull
    @NotBlank
    private String fitchRating;

    @NotNull
    @Min(0)
    private Integer orderNumber;

}
