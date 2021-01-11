package pl.edu.pjwstk.jaz.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.controllers.AppAuthentication;
import pl.edu.pjwstk.jaz.deprecated.UserDB;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;
import pl.edu.pjwstk.jaz.repositories.UserRepository;


@Component
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        UserEntity user = userRepository.findUserByUsername(username);
        if (userRepository.matches(password, user.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }
}
