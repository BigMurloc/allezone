package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//TODO better initialization of DBMock
@Component
public class UserDB {
    private User adminUser = new User("admin", "admin");
    private User exampleUser = new User("example", "kotek");
    private User moderatorUser = new User("moderator", "1234");
    private List<User> users = new ArrayList<>();


    public void initializeDB(){
        users.add(adminUser);
        users.add(exampleUser);
        users.add(moderatorUser);
    }

    public List<User> getListOfUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

}
