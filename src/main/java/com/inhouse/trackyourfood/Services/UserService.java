package com.inhouse.trackyourfood.Services;

import org.springframework.stereotype.Service;

import com.inhouse.trackyourfood.Entities.User;
import com.inhouse.trackyourfood.Entities.WeightGoal;
import com.inhouse.trackyourfood.Types.BMILabel;
import com.inhouse.trackyourfood.Types.PlanLabel;
import com.inhouse.trackyourfood.Types.WeightPlan;

@Service
public class UserService {

    public static User updateBMI(User user) {
        float height = (float) user.getHeight() / 100; // cm to m
        float bmi = (float) (user.getWeight() / (height * height));
        user.setBMI(bmi);
        if (bmi < 18.4) {
            user.setBmiLabel(BMILabel.UNDERWEIGHT);
        } else if (bmi < 25) {
            user.setBmiLabel(BMILabel.NORMAL);
        } else if (bmi < 30) {
            user.setBmiLabel(BMILabel.OVERWEIGHT);
        } else {
            user.setBmiLabel(BMILabel.OBESE);
        }

        return user;
    }

    public static User updateBMR(User user) {
        user.setBMR((int) (66.4730 + 13.7516 * user.getWeight() + 5.0033 * user.getHeight() - 6.7550 * user.getAge()));
        return user;
    }

    public static float getGoalCompletionPercentage(User user, WeightGoal goal) {
        float diffWeight = goal.getStartWeight() - goal.getTargetWeight();
        float currChange = goal.getStartWeight() - user.getWeight();

        return currChange / diffWeight * 100;
    }

    public static boolean isGoalWeightReached(User user, WeightGoal goal) {
        return user.getWeight() == goal.getTargetWeight();
    }

    public static User copyFromUser(User source, User target) {
        if (source.getAge() != 0) {
            target.setAge(source.getAge());
        }
        if (source.getHeight() != 0) {
            target.setHeight(source.getHeight());
        }
        if (source.getWeight() != 0) {
            target.setWeight(source.getWeight());
        }

        return UserService.updateBMR(UserService.updateBMI(target));
    }

    public static void getWeightGoalPlans(User user, WeightGoal goal) {
        float reqChange = goal.getTargetWeight() - user.getWeight();

        WeightPlan aggressivWeightPlan = new WeightPlan();
        WeightPlan balancedWeightPlan = new WeightPlan();
        WeightPlan passiveWeightPlan = new WeightPlan();

    }

}
