package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    User user;
    private final UserDB userDB;

    public AuthenticationService(UserDB userDB) {
        this.userDB = userDB;
    }

    public boolean login(String username, String password) {
        user = userDB.findUser(username, password);
        if(user != null)
            return user.getPassword().equals(password);
        return false;
    }

}
