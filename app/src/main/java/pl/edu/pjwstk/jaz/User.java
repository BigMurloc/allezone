package pl.edu.pjwstk.jaz;

//TODO randomSessionID generation method
public class User {
    private String username;
    private String password;
    private String userSessionID;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserSessionID() {
        return userSessionID;
    }

    public void setUserSessionID(String userSessionID) {
        this.userSessionID = userSessionID;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
