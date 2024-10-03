package com.inhouse.trackyourfood.Entities;

import com.inhouse.trackyourfood.Types.BMILabel;

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
public class User {

    @Id
    @GeneratedValue
    long id;

    @Column
    @NotBlank
    String name;

    @Column
    @NotBlank
    String email;

    @Column
    @Positive
    int age;

    // in kgs
    @Column
    @Positive
    float weight;

    // In cms
    @Column
    @Positive
    int height;

    @Column
    float BMI;

    @Column
    float BMR;

    @Column
    long goalId;

    @Column
    @Enumerated(EnumType.STRING)
    BMILabel bmiLabel;
}
