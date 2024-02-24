package hu.cubix.userservice.patrik.controller;

import hu.cubix.userservice.patrik.service.FacebookLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class FacebookLoginController {

    private final FacebookLoginService loginService;

    @RequestMapping("/fbLoginSuccess")
    public String onFacebookLoginSuccess(OAuth2AuthenticationToken authenticationToken) {
        loginService.registerFbUserIfNeeded(authenticationToken);
        return "home";
    }
}
