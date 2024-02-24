package hu.cubix.userservice.patrik.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;
    private String facebookToken;
}
