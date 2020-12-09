package pl.edu.pjwstk.jaz.deprecated;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private final Set<String> authorities;

    public User(String username, String password, Set<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.authorities = new HashSet<>();
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addAuthority(String authority){
        this.authorities.add(authority);
    }

}
