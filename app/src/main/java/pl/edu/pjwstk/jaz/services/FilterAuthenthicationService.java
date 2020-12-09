package pl.edu.pjwstk.jaz.services;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.deprecated.User;
import pl.edu.pjwstk.jaz.deprecated.UserDB;
import pl.edu.pjwstk.jaz.filter.UserSession;


@Component
public class FilterAuthenthicationService {

    User user;
    private final UserDB userDB;
    private final UserSession userSession;

    public FilterAuthenthicationService(UserDB userDB, UserSession userSession) {
        this.userDB = userDB;
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {
        user = userDB.getUser(username);
        if(user != null && user.getPassword().equals(password)){
            userSession.logIn();
            return true;
        }
        return false;
    }

}
