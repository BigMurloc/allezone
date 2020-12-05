package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.requests.AuthorityRequest;
import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.UserDB;

@RestController
public class GrantAuthorityController {

    private final UserDB userDB;

    public GrantAuthorityController(UserDB userDB) {
        this.userDB = userDB;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'grant-authority')")
    @PostMapping("grant-authority")
    public void grantRole(@RequestBody AuthorityRequest authorityRequest){
        User user = userDB.getUser(authorityRequest.getUsername());
        if (user != null){
            user.addAuthority(authorityRequest.getAuthority());
        }
    }

}
