package com.inhouse.trackyourfood.Services;

import java.lang.reflect.Field;

import com.inhouse.trackyourfood.Entities.Food;

public class FoodService {
    public static Food copyWeightGoal(Food source, Food target) {

        for (Field field : Food.class.getDeclaredFields()) {
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
