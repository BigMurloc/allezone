package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO check if user already exists
@RestController
public class RegisterController {

    private final UserDB userDB;

    public RegisterController(UserDB userDB) {
        this.userDB = userDB;
    }

    @PostMapping("auth0/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
        userDB.addUser(user);
    }

}
