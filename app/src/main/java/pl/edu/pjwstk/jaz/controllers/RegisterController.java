package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;
import pl.edu.pjwstk.jaz.repositories.UserRepository;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.deprecated.UserDB;

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
