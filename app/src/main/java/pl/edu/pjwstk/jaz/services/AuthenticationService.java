package pl.edu.pjwstk.jaz.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.*;
import pl.edu.pjwstk.jaz.entities.UserEntity;


@Component
public class AuthenticationService {

    User user;
    private final UserDB userDB;
    private final UserSession userSession;
    private final UserRepository userRepository;

    public AuthenticationService(UserDB userDB, UserSession userSession, UserRepository userRepository) {
        this.userDB = userDB;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        user = userDB.getUser(username);
        UserEntity userEntity = userRepository.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }
}
