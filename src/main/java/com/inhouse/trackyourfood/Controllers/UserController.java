package com.inhouse.trackyourfood.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.trackyourfood.Entities.User;
import com.inhouse.trackyourfood.Entities.WeightGoal;
import com.inhouse.trackyourfood.Repositories.UserRepository;
import com.inhouse.trackyourfood.Repositories.WeightGoalRepository;
import com.inhouse.trackyourfood.Services.UserService;

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
    public long addNewWeightGoal(@Valid @RequestBody WeightGoal weightGoal) {
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

}
