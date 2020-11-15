package pl.edu.pjwstk.jaz;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//TODO find better way to compare sessionID
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {

    private String sessionID;

    private final UserDB userDB;

    public UserSession(UserDB userDB) {
        this.userDB = userDB;
    }

    public boolean isLoggedIn() {
        for (User user : userDB.getListOfUsers()) {
            if( sessionID != null && sessionID.equals(user.getUserSessionID())) {
                return true;
            }
        }
        return false;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

}
