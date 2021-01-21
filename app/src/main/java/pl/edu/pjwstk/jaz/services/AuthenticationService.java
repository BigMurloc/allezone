package pl.edu.pjwstk.jaz.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.controllers.AppAuthentication;
import pl.edu.pjwstk.jaz.repositories.entities.User;
import pl.edu.pjwstk.jaz.repositories.UserRepository;


@Component
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (userRepository.matches(password, user.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }
}
