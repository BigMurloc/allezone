package pl.edu.pjwstk.jaz;

import org.hibernate.criterion.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO if any of users is already logged in - log them out and proceed to logging current user
@RestController
public class LoginController {

    private final AuthenticationService authenticationService;
    public LoginController(AuthenticationService authenticationService, AppWebSecurityConfig appWebSecurityConfig) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        //uwierzetylnianie
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

}
