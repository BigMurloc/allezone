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
//    private final UserDB userDB;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterController(UserRepository userRepository, UserDB userDB) {
        this.userRepository = userRepository;
//        this.userDB = userDB;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        UserEntity userEntity = createUserEntity(registerRequest);
        userRepository.saveUser(userEntity);
    }

    private UserEntity createUserEntity(RegisterRequest registerRequest){
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setFirstName(registerRequest.getFirstName());
        userEntity.setLastName(registerRequest.getLastName());
        userEntity.setPesel(registerRequest.getPesel());
        userEntity.setPhoneNumber(registerRequest.getPhoneNumber());

        return userEntity;
    }


}
