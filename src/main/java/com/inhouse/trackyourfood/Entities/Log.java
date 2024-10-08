package com.inhouse.trackyourfood.Entities;

import java.sql.Timestamp;

import com.inhouse.trackyourfood.Types.LogCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long autoId;

    @Column
    long id;

    @Column
    long userId;

    @Column
    @Enumerated(EnumType.STRING)
    LogCategory logCategory;

    @Column
    @NotNull
    Timestamp timestamp;

    @Column
    long calories;

    // item ids
    @Column
    long items[];

    @Column
    double quantities[];

}
