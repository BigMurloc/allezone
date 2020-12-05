package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.UserRepository;
import pl.edu.pjwstk.jaz.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.exceptions.UserAlreadyExistsException;
import pl.edu.pjwstk.jaz.UserDB;

@RestController
public class RegisterController {

    private final UserRepository userRepository;
    private final UserDB userDB;

    public RegisterController(UserRepository userRepository, UserDB userDB) {
        this.userRepository = userRepository;
        this.userDB = userDB;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if(userDB.doesExist(registerRequest.getUsername()))
            throw new UserAlreadyExistsException();
        userDB.addUser(registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.saveUser(registerRequest);
    }



}
