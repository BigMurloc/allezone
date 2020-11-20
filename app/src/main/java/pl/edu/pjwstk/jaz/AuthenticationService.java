package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    private final UserDB userDB;

    public AuthenticationService(UserDB userDB) {
        this.userDB = userDB;
    }

    public boolean login(String username, String password) {
        return userDB.compareUserCredentials(username, password);
    }

}
