package hu.cubix.userservice.patrik.service;

import hu.cubix.userservice.patrik.model.UserModel;
import hu.cubix.userservice.patrik.repository.UserModelRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class InitDbService {

    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void deleteDb() {
        userModelRepository.deleteAllInBatch();
    }

    @Transactional
    public void createUsersIfNeeded() {
        if(!userModelRepository.existsById("admin")) {
            userModelRepository.save(
                    new UserModel("admin", "admin@test.com", passwordEncoder.encode("pass"), Set.of("admin", "customer")));
        }
        if(!userModelRepository.existsById("user")) {
            userModelRepository.save(
                    new UserModel("user", "user@test.com", passwordEncoder.encode("pass"), Set.of("customer")));
        }
    }
}
