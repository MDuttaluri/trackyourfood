package com.inhouse.trackyourfood.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class WeightGoal {

    @Id
    @GeneratedValue
    long id;

    @Column
    @NotNull
    long userId;

    @Column
    @NotNull
    float targetWeight;

    @Column
    @NotNull
    Timestamp targetDate;

    @Column
    @NotNull
    Timestamp startDate;

    @Column
    @NotNull
    float startWeight;

    @Column
    @NotNull
    boolean isActive;

}
