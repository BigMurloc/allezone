package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDB {
    //todo change to hashMap
    private User adminUser = new User("admin", "admin");
    private User exampleUser = new User("example", "kotek");
    private User moderatorUser = new User("moderator", "1234");
    private List<User> users = new ArrayList<>(List.of(adminUser, exampleUser, moderatorUser));
    public List<User> getListOfUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

}
