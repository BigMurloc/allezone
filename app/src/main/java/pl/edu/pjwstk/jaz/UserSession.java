package pl.edu.pjwstk.jaz;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {

    private final UserDB userDB;

    private List<User> userList;

    public UserSession(UserDB userDB) {
        this.userDB = userDB;
        userList = userDB.getListOfUsers();
    }

    public boolean isLoggedIn() {
        for (User user : userList) {
            if(user.isLoggedIn())
                return true;
        }
        return false;
    }


}
