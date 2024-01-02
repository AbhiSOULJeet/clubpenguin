package com.abhijeet.web.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhijeet.web.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String userName);

    @Query("select password from users where username=':Username'")
    String findPasswordbyUsername(String Username);

    UserEntity findFirstByUsername(String username);
}
