package com.inhouse.trackyourfood.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhouse.trackyourfood.Entities.User;
import com.inhouse.trackyourfood.Repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        System.out.println("Request for GETUSERBYID" + userId);
        return userRepository.findById(userId).get();
    }

    @PostMapping("/addUser")
    public long addNewUser(@Valid @RequestBody User user) {
        return userRepository.save(user).getId();
    }

}
