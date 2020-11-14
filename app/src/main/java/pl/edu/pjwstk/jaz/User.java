package pl.edu.pjwstk.jaz;

//TODO try to change boolean value for sessionID and then compare them if the current user sessionID
// is equal to UserSessionID
public class User {
    private String username;
    private String password;
    private boolean isLoggedIn = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
