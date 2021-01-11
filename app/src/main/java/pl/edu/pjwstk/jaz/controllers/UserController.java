package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;
import pl.edu.pjwstk.jaz.repositories.UserRepository;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;

@RestController
public class UserController {
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public UserEntity getUserEntity(@PathVariable String username) throws UnauthorizedException {
        UserEntity currentUser =
        (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if(currentUser.getUsername().equals(username) || currentUser.getAuthority().contains("admin"))
            return userRepository.findUserByUsername(username);
        throw new UnauthorizedException();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/deleteUser/{username}")
    public void deleteUser(@PathVariable String username){
       userRepository.deleteUser(username);
    }

}

