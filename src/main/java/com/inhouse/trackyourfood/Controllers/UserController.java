package com.inhouse.trackyourfood.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.trackyourfood.Entities.User;
import com.inhouse.trackyourfood.Entities.WeightGoal;
import com.inhouse.trackyourfood.Repositories.UserRepository;
import com.inhouse.trackyourfood.Repositories.WeightGoalRepository;
import com.inhouse.trackyourfood.Services.UserService;
import com.inhouse.trackyourfood.Services.WeightGoalService;
import com.inhouse.trackyourfood.Types.GoalRequest;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeightGoalRepository weightGoalRepository;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        System.out.println("Request for GETUSERBYID" + userId);
        return userRepository.findById(userId).get();
    }

    @PostMapping("/update")
    public long updateUser(@RequestBody User user) throws Exception {
        User dbUser = userRepository.findById(user.getId()).get();
        if (dbUser == null) {
            throw new Exception("No user found.");
        }
        UserService.copyFromUser(user, dbUser);
        userRepository.save(dbUser);
        return dbUser.getId();

    }

    @PostMapping("/addUser")
    public long addNewUser(@Valid @RequestBody User user) {
        user = UserService.updateBMR(UserService.updateBMI(user));
        return userRepository.save(user).getId();
    }

    @PostMapping("/addWeightGoal")
    public long addNewWeightGoal(@Valid @RequestBody WeightGoal weightGoal) throws Exception {
        User user = userRepository.findById(weightGoal.getUserId()).get();
        if (user == null) {
            throw new Exception("No user found.");
        }
        weightGoal.setStartWeight(user.getWeight());
        weightGoal = WeightGoalService.updateCaloriesPerDay(weightGoal);
        return weightGoalRepository.save(weightGoal).getId();
    }

    @GetMapping("/activeGoals/{userId}")
    public List<WeightGoal> getMethodName(@PathVariable long userId) {
        return weightGoalRepository.findAllByUserIdAndIsActiveTrue(userId);
    }

    @GetMapping("/activeGoals")
    public List<WeightGoal> getMethodName() {
        return weightGoalRepository.findAll();
    }

    @GetMapping("/weightGoals")
    public List<WeightGoal> getWeightGoals(@Valid @RequestBody GoalRequest request) throws Exception {

        User user = userRepository.findById(request.getUserId()).get();

        if (user == null)
            throw new Exception("User not found.");

        List<WeightGoal> resultList = weightGoalRepository.findByUserId(user.getId());
        return resultList.stream()
                .filter(goal -> request.isQueryActive() ? (goal.isActive() == request.isActive()) : true)
                .filter(goal -> request.isQueryLabel() ? (goal.getLabel() == request.getLabel()) : true)
                .toList();

    }

    @GetMapping("/weightGoal/{goalId}")
    public WeightGoal getWeightGoal(@PathVariable long goalId) {
        return weightGoalRepository.findById(goalId).get();
    }

    @PostMapping("/weightGoal/update")
    public long updateWeightGoal(@Valid @RequestBody WeightGoal request) throws Exception {

        WeightGoal goal = weightGoalRepository.findById(request.getId()).get();
        if (goal == null) {
            throw new Exception("No goal found.");
        }

        goal = WeightGoalService.copyWeightGoal(request, goal);
        WeightGoalService.updateCaloriesPerDay(goal);
        weightGoalRepository.save(goal);
        return goal.getId();
    }

}
