package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO if any of users is already logged in - log them out and proceed to logging current user
@RestController
public class LoginController {

    UserSession userSession;

    private final AuthenticationService authenticationService;
    public LoginController(AuthenticationService authenticationService, UserSession userSession) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
        userSession.logIn();
    }

}
