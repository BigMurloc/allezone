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

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if(userDB.doesExist(registerRequest.getUsername()))
            throw new UserAlreadyExistsException();
        userDB.addUser(registerRequest.getUsername(), registerRequest.getPassword());
    }



}
