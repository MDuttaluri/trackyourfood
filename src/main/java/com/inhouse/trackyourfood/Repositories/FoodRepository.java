package com.inhouse.trackyourfood.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhouse.trackyourfood.Entities.Food;
import com.inhouse.trackyourfood.Types.FoodCategory;

public interface FoodRepository extends JpaRepository<Food, Long> {

    public List<Food> findByNameStartingWithIgnoreCase(String prefix);

    public List<Food> findByCaloriesLessThanEqual(float limit);

    public List<Food> findByFoodCategoryIn(List<FoodCategory> foodCategories);
}
