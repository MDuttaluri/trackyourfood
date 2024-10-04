package com.inhouse.trackyourfood.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.trackyourfood.Entities.Food;
import com.inhouse.trackyourfood.Repositories.FoodRepository;
import com.inhouse.trackyourfood.Services.FoodService;
import com.inhouse.trackyourfood.Types.FoodCategory;
import com.inhouse.trackyourfood.Types.FoodRequest;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/food")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/add")
    public long addNewFood(@Valid @RequestBody Food food) {
        return foodRepository.save(food).getId();
    }

    @PostMapping("/update")
    public long updateFood(@RequestBody Food food) throws Exception {
        Food dbFood = foodRepository.findById(food.getId()).get();
        if (dbFood == null) {
            throw new Exception("No food found.");
        }

        dbFood = FoodService.copyWeightGoal(food, dbFood);
        foodRepository.save(dbFood);
        return dbFood.getId();
    }

    @GetMapping("/{foodId}")
    public Food getFood(@PathVariable long foodId) {
        return foodRepository.findById(foodId).get();
    }

    @GetMapping("/like/{prefix}")
    public List<Food> getFoodLike(@PathVariable String prefix) {
        return foodRepository.findByNameStartingWithIgnoreCase(prefix);
    }

    @GetMapping("/calorieLimit/{limit}")
    public List<Food> getFoodUnderCalorieLimit(@PathVariable float limit) {
        return foodRepository.findByCaloriesLessThanEqual(limit);
    }

    @GetMapping("/fromCategories")
    public List<Food> getFoodByCateogry(@RequestBody List<FoodCategory> foodCategories) {
        return foodRepository.findByFoodCategoryIn(foodCategories);
    }

    @GetMapping
    public List<Food> getFoods(@Valid @RequestBody FoodRequest request) {

        List<Food> foods = foodRepository.findAll();

        return foods.stream()
                .filter(food -> request.getQueryPrefix() ? food.getName().startsWith(request.getPrefix()) : true)
                .filter(food -> request.getQueryCalorieLimit() ? food.getCalories() <= request.getCalorieLimit() : true)
                .filter(food -> {
                    if (!request.getQueryCategory())
                        return true;
                    for (FoodCategory fCategory : food.getFoodCategory()) {
                        if (request.getFoodCategories().contains(fCategory))
                            return true;
                    }
                    return false;
                })
                .toList();

    }

}
