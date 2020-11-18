package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDB {
    //todo change to hashMap
    //todo what to use as a key in hashMap?
    private User admin = new User("admin", "admin");
    private User example = new User("example", "kotek");
    private User moderator = new User("moderator", "1234");
    private HashMap<String, User> users = new HashMap<>();

    public UserDB() {
        this.users.put("0", admin);
        this.users.put("1", example);
        this.users.put("2", moderator);
    }

    public void addUser(User user){
        String key = String.valueOf(users.size());
        users.put(key, user);
    }

}
