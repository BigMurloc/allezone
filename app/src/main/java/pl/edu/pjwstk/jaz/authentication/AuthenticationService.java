package pl.edu.pjwstk.jaz.authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.user.User;
import pl.edu.pjwstk.jaz.user.UserDB;
import pl.edu.pjwstk.jaz.UserSession;


@Component
public class AuthenticationService {

    User user;
    private final UserDB userDB;
    private final UserSession userSession;

    public AuthenticationService(UserDB userDB, UserSession userSession) {
        this.userDB = userDB;
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {
        user = userDB.getUser(username);
        if(user != null && user.getPassword().equals(password)){
            userSession.logIn();
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }

}
