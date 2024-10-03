package com.inhouse.trackyourfood.Types;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoalRequest {

    @NotNull
    boolean queryLabel;
    PlanLabel label;

    @NotNull
    boolean queryActive;
    boolean isActive;

    @NotNull
    long userId;

    long id;
}
