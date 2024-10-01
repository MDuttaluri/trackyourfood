package com.inhouse.trackyourfood.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inhouse.trackyourfood.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
