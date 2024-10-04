package com.inhouse.trackyourfood.Types;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodRequest {

    @NotNull
    Boolean queryPrefix;
    String prefix;

    @NotNull
    Boolean queryCalorieLimit;
    float calorieLimit;

    @NotNull(message = "Mention usage of category filter")
    Boolean queryCategory;
    List<FoodCategory> foodCategories;

}
