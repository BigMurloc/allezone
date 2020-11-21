package pl.edu.pjwstk.jaz;

import liquibase.pro.packaged.U;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//todo napisac testy do klas
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
        for (int i = 0; i < 10000000; i++) {
            String s = Long.toString(i);
            User user = new User(s,s);
            this.users.put(user.getUsername(), user);
        }
    }

    public void addUser(User user){
        users.put(user.getUsername(), user);
        System.out.println(users.size());
    }

    public User findUser(String username, String password) {
        if(users.containsKey(username))
            return users.get(username);
        return null;
    }

    public boolean doesExist(String username){
        return users.containsKey(username);
    }

}
