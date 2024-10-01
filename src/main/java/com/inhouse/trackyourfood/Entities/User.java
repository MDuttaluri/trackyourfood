package com.inhouse.trackyourfood.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
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

}
