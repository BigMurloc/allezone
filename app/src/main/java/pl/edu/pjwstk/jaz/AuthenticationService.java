package pl.edu.pjwstk.jaz;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


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
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
            return true;
        }
        return false;
    }

}
