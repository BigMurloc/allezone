package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserSession userSession;

    private final AuthenticationService authenticationService;
    public LoginController(AuthenticationService authenticationService, UserSession userSession) {
        this.authenticationService = authenticationService;
        this.userSession = userSession;
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
