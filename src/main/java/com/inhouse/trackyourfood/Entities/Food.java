package com.inhouse.trackyourfood.Entities;

import java.util.List;

import com.inhouse.trackyourfood.Types.FoodCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue
    long id;

    @Column
    @NotBlank
    String name;

    @Column
    @Enumerated(EnumType.STRING)
    List<FoodCategory> foodCategory;

    @Column
    @Positive
    float calories;

    @Column
    String servingSize;

    @Column
    List<String> ingredients;

    @Column
    List<String> macros;

}
