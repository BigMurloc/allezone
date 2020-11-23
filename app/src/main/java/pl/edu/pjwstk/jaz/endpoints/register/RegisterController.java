package pl.edu.pjwstk.jaz.endpoints.register;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.user.User;
import pl.edu.pjwstk.jaz.user.UserDB;

import java.util.Collections;

@RestController
public class RegisterController {

    private final UserDB userDB;

    public RegisterController(UserDB userDB) {
        this.userDB = userDB;
    }

    @PostMapping("/auth0/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), registerRequest.getPassword(), Collections.emptySet());
        if(userDB.doesExist(user.getUsername()))
            System.out.println("Uzytkownik o danym loginie juz istnieje");
        else{
            userDB.addUser(user);
        }
    }

}
