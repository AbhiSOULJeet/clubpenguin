package com.abhijeet.web.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.RegistrationDto;
import com.abhijeet.web.models.UserEntity;

@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String userName);

    String findPasswordByUsern(String Username);

}
