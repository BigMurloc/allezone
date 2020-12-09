package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.repositories.UserRepository;
import pl.edu.pjwstk.jaz.entities.UserEntity;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo na strone moze wejsc tylko ten uzytkownik
    @GetMapping("/{username}")
    public UserEntity getUserEntity(@PathVariable String username){
        return userRepository.findUserByUsername(username);
    }

    //todo only admin or user
    @PostMapping("/deleteUser/{username}")
    public void deleteUser(@PathVariable String username){
       userRepository.deleteUser(username);
    }

}

