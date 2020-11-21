package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UserDB userDB;

    public RegisterController(UserDB userDB) {
        this.userDB = userDB;
    }

    @PostMapping("/auth0/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
        if(userDB.doesExist(user.getUsername()))
            System.out.println("Uzytkownik o danym loginie juz istnieje");
        else{
            userDB.addUser(user);
        }
    }

}
