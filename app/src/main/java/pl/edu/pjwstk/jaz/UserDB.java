package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDB {
    private User admin = new User("admin", "admin");
    private User example = new User("example", "kotek");
    private User moderator = new User("moderator", "1234");
    private HashMap<String, User> users = new HashMap<>();

    public UserDB() {
        this.users.put(admin.getUsername(), admin);
        this.users.put(example.getUsername(), example);
        this.users.put(moderator.getUsername(), moderator);
    }

    public void addUser(User user){
        users.put(user.getUsername(), user);
        for(Map.Entry<String, User> set : users.entrySet()) {
            System.out.println(set.getValue().getUsername());
        }
        System.out.println(users.size());
    }

}
