package hu.cubix.userservice.patrik.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private String username;
    private String email;
    private String password;
}
