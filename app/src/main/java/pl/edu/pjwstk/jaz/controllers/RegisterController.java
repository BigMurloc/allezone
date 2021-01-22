package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.database.repositories.UserRepository;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;

@RestController
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        userRepository.saveUser(registerRequest);
    }

}
