package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.repositories.UserRepository;
import pl.edu.pjwstk.jaz.controllers.requests.AuthorityRequest;
import pl.edu.pjwstk.jaz.deprecated.UserDB;

@RestController
public class GrantAuthorityController {

//    private final UserDB userDB;
    private final UserRepository userRepository;

    public GrantAuthorityController(UserDB userDB, UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.userDB = userDB;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'grant-authority')")
    @PostMapping("grant-authority")
    public void grantRole(@RequestBody AuthorityRequest authorityRequest){
//        User user = userDB.getUser(authorityRequest.getUsername());
//        if (user != null){
//            user.addAuthority(authorityRequest.getAuthority());
//        }
        userRepository.grantAuthority(authorityRequest);
    }

    @PostMapping("/revoke-authority")
    public void revokeAuthority(@RequestBody AuthorityRequest authorityRequest){
        userRepository.revokeAuthority(authorityRequest);
    }
}
