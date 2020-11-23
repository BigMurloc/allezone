package pl.edu.pjwstk.jaz.endpoints.grantAuthority;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.user.User;
import pl.edu.pjwstk.jaz.user.UserDB;

@RestController
public class AuthorityController {

    private final UserDB userDB;

    public AuthorityController(UserDB userDB) {
        this.userDB = userDB;
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("auth0/grant-authority")
    public void grantRole(@RequestBody AuthorityRequest authorityRequest){
        User user = userDB.getUser(authorityRequest.getUsername());
        if (user != null){
            user.addAuthority(authorityRequest.getAuthority());
        }
    }


}
