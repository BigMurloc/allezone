package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.database.repositories.UserRepository;
import pl.edu.pjwstk.jaz.controllers.requests.AuthorityRequest;

@RestController
public class AuthorityController {

    private final UserRepository userRepository;

    public AuthorityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'grant-authority')")
    @PostMapping("grant-authority")
    public void grantRole(@RequestBody AuthorityRequest authorityRequest){
        userRepository.grantAuthority(authorityRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'revoke-authority')")
    @PostMapping("/revoke-authority")
    public void revokeAuthority(@RequestBody AuthorityRequest authorityRequest){
        userRepository.revokeAuthority(authorityRequest);
    }
}
