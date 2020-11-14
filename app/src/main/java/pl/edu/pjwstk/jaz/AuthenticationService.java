package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationService {

    private final UserDB userDB;

    public AuthenticationService(UserDB userDB) {
        this.userDB = userDB;
    }


    public boolean login(String username, String password) {
        userDB.initializeDB();
        List<User> users = userDB.getListOfUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
            if(username.equals(user.getUsername())
            && password.equals(user.getPassword())) {
                user.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

}
