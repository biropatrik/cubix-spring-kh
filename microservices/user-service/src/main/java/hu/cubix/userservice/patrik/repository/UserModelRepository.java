package hu.cubix.userservice.patrik.repository;

import hu.cubix.userservice.patrik.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByFacebookId(String facebookId);
}
