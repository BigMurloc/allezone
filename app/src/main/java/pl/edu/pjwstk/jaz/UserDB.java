package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class UserDB {
    private User admin;
    private User example = new User("user", "user");
    private User moderator = new User("moderator", "1234");
    private HashMap<String, User> users = new HashMap<>();

    public UserDB() {
        Set<String> adminAuthority = new HashSet<>();
        adminAuthority.add("admin"); //
        admin = new User("admin", "admin", adminAuthority);
        this.users.put(admin.getUsername(), admin);
        this.users.put(example.getUsername(), example);
        this.users.put(moderator.getUsername(), moderator);
        simulateLargeUserDB();
    }

    private void simulateLargeUserDB() {
        for (int i = 0; i < 10_000; i++) {
            String s = Long.toString(i);
            User user = new User(s,s, Collections.emptySet());
            this.users.put(user.getUsername(), user);
        }
    }

    public void addUser(String username, String password){
        User user = new User(username, password);
        users.put(user.getUsername(), user);
        System.out.println(users.size());
    }

    public User getUser(String username) {
        if(users.containsKey(username))
            return users.get(username);
        return null;
    }

    public boolean doesExist(String username){
        return users.containsKey(username);
    }

}
