package pl.edu.pjwstk.jaz.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.*;
import pl.edu.pjwstk.jaz.deprecated.UserDB;
import pl.edu.pjwstk.jaz.entities.UserEntity;
import pl.edu.pjwstk.jaz.repositories.UserRepository;


@Component
public class AuthenticationService {

//    User user;
//    private final UserDB userDB;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthenticationService(UserDB userDB, UserRepository userRepository) {
//        this.userDB = userDB;
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
//        user = userDB.getUser(username);
        UserEntity userEntity = userRepository.findUserByUsername(username);
        if (passwordEncoder.matches(password, userEntity.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userEntity));
            return true;
        }
        return false;
    }
}
