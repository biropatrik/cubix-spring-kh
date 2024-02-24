package hu.cubix.userservice.patrik.service;

import hu.cubix.tokenlib.patrik.JwtService;
import hu.cubix.userservice.patrik.dto.LoginDto;
import hu.cubix.userservice.patrik.dto.RegistrationDto;
import hu.cubix.userservice.patrik.model.UserModel;
import hu.cubix.userservice.patrik.repository.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserModelRepository userModelRepository;
    private final FacebookLoginService facebookLoginService;

    public String registerNewUser(RegistrationDto registrationDto) {
        if (!userModelRepository.existsById(registrationDto.getUsername())) {
            userModelRepository.save(
                    new UserModel(registrationDto.getUsername(), registrationDto.getEmail(),
                            passwordEncoder.encode(registrationDto.getPassword()), Set.of("customer")));
        }
        return authenticate(registrationDto.getUsername(), registrationDto.getPassword());
    }

    public String authenticate(LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto.getFacebookToken())) {
            return authenticate(loginDto.getUsername(), loginDto.getPassword());
        }
        return "\"" + jwtService.creatJwtToken(
                            facebookLoginService.getUserDetailsForToken(loginDto.getFacebookToken())) + "\"";
    }

    private String authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return "\"" + jwtService.creatJwtToken((UserDetails) authentication.getPrincipal()) + "\"";
    }
}
