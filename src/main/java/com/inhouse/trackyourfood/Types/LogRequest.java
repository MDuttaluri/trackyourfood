package com.inhouse.trackyourfood.Types;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogRequest {
    @NotNull
    LogInterval interval;

    @NotNull
    long userId;

    @NotNull
    Timestamp timestamp;

    @NotNull
    long duration;

}
