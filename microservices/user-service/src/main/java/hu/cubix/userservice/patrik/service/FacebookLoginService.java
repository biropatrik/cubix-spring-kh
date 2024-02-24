package hu.cubix.userservice.patrik.service;

import hu.cubix.userservice.patrik.model.UserModel;
import hu.cubix.userservice.patrik.repository.UserModelRepository;
import hu.cubix.userservice.patrik.security.OwnUserDetailsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FacebookLoginService {
    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v13.0";

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;
    private final OAuth2AuthorizedClientService authClientService;

    @Getter
    @Setter
    public static class FacebookData {
        private String email;
        private long id;
    }

    @Transactional
    public UserDetails getUserDetailsForToken(String fbToken) {
        FacebookData fbData = getEmailOfFbUser(fbToken);
        UserModel user = findOrCreateUser(fbData);
        return OwnUserDetailsService.createUserDetails(user);
    }

    private FacebookData getEmailOfFbUser(String fbToken) {
        return WebClient.create(GRAPH_API_BASE_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/me")
                        .queryParam("fields", "email,name")
                        .build()
                )
                .headers(headers -> headers.setBearerAuth(fbToken))
                .retrieve()
                .bodyToMono(FacebookData.class)
                .block();
    }

    private UserModel findOrCreateUser(FacebookData facebookData) {
        String fbId = String.valueOf(facebookData.getId());
        Optional<UserModel> optionalExistingUser = userModelRepository.findByFacebookId(fbId);
        if(optionalExistingUser.isEmpty()) {
            UserModel newUser = new UserModel(
                    facebookData.getEmail(),
                    passwordEncoder.encode("dummy"),
                    Set.of("customer"));
            newUser.setFacebookId(fbId);

            return userModelRepository.save(newUser);
        }
        return optionalExistingUser.get();
    }

    public void registerFbUserIfNeeded(OAuth2AuthenticationToken authenticationToken) {
        String fbId = authenticationToken.getName();
        OAuth2User oauth2User = authenticationToken.getPrincipal();
        System.out.println("FB id from oauth2User:" + oauth2User.getName());
        Object email = oauth2User.getAttribute("email");
        System.out.println("email:" + email);

        String authorizedClientRegistrationId = authenticationToken.getAuthorizedClientRegistrationId();

        OAuth2AuthorizedClient client = authClientService.loadAuthorizedClient(authorizedClientRegistrationId, fbId);
        System.out.println("access token:" + client.getAccessToken().getTokenValue());

        Optional<UserModel> optionalExistingUser = userModelRepository.findByFacebookId(fbId);
        if (optionalExistingUser.isEmpty()) {
            UserModel newUser = new UserModel(
                    email.toString(),
                    passwordEncoder.encode("dummy"),
                    Set.of("customer"));
            newUser.setFacebookId(fbId);
            userModelRepository.save(newUser);
        }
    }
}
