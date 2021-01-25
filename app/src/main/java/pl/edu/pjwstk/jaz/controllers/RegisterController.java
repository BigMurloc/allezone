package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.database.services.UserService;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.exceptions.UserAlreadyExistsException;

@RestController
public class RegisterController {

    private final UserService userRepository;

    public RegisterController(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        userRepository.saveUser(registerRequest);
    }

}
