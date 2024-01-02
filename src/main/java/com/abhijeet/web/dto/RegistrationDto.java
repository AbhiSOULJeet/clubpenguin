package com.abhijeet.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @NotEmpty(message = "email can't be empty")
    private String email;
    @NotEmpty(message = "password can't be empty")
    private String password;
}
