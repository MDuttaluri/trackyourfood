package com.inhouse.trackyourfood.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhouse.trackyourfood.Entities.WeightGoal;

public interface WeightGoalRepository extends JpaRepository<WeightGoal, Long> {
    public List<WeightGoal> findByUserId(long userId);

    public List<WeightGoal> findAllByUserIdAndIsActiveTrue(long userId);
}
