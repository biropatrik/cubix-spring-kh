package hu.cubix.userservice.patrik.security;

import hu.cubix.userservice.patrik.model.UserModel;
import hu.cubix.userservice.patrik.repository.UserModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OwnUserDetailsService implements UserDetailsService {

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userModelRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return  createUserDetails(userModel);
    }

    public static UserDetails createUserDetails(UserModel userModel) {
        return new User(userModel.getUsername(), userModel.getPassword(),
                        userModel.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
