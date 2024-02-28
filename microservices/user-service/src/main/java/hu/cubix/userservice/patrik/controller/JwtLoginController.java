package hu.cubix.userservice.patrik.controller;

import hu.cubix.userservice.patrik.dto.LoginDto;
import hu.cubix.userservice.patrik.dto.RegistrationDto;
import hu.cubix.userservice.patrik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtLoginController {

    @Autowired
    UserService userService;

    @PostMapping("/api/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.authenticate(loginDto);
    }

    @PostMapping("/api/registration")
    public String registration(@RequestBody RegistrationDto registrationDto) {
        return userService.registerNewUser(registrationDto);
    }
}
