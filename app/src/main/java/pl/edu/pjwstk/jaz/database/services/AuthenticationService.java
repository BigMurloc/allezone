package pl.edu.pjwstk.jaz.database.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.controllers.AppAuthentication;
import pl.edu.pjwstk.jaz.database.entities.User;


@Component
public class AuthenticationService {

    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (userService.matches(password, user.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }
}
