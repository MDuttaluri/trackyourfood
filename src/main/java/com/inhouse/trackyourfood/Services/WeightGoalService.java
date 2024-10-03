package com.inhouse.trackyourfood.Services;

import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

import com.inhouse.trackyourfood.Entities.WeightGoal;
import com.inhouse.trackyourfood.Types.PlanLabel;

@Service
public class WeightGoalService {
    public static WeightGoal updateCaloriesPerDay(WeightGoal goal) {
        float reqChange = goal.getTargetWeight() - goal.getStartWeight();

        long diff = goal.getTargetDate().getTime() - goal.getStartDate().getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (hours > 0) {
            days += 1;
        }

        float caloriesPerDay = (reqChange * 7700) / days;
        goal.setCaloriesPerDay(caloriesPerDay);

        int absCaloriesPerDay = Math.abs((int) caloriesPerDay);

        if (absCaloriesPerDay <= 400) {
            goal.setLabel(PlanLabel.PASSIVE);
        } else if (absCaloriesPerDay <= 700) {
            goal.setLabel(PlanLabel.BALANCED);
        } else if (absCaloriesPerDay <= 1000) {
            goal.setLabel(PlanLabel.AGGRESSIVE);
        } else {
            goal.setLabel(PlanLabel.NOTRECOMMENDED);
        }
        return goal;
    }

    public static WeightGoal copyWeightGoal(WeightGoal source, WeightGoal target) {

        for (Field field : WeightGoal.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                var value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (Exception e) {
                continue;
            }
        }

        return target;
    }
}
