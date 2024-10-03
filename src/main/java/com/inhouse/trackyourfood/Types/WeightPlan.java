package com.inhouse.trackyourfood.Types;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class WeightPlan {
    long userId;
    long goalId;
    int duration;
    int caloriesPerDay;

    @Enumerated(EnumType.STRING)
    PlanLabel label;

}
