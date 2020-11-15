package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO if any of users is already logged in - log them out and proceed to logging current user
@RestController
public class LoginController {

    private final AuthenticationService authenticationService;
    private final UserSession userSession;
    public LoginController(AuthenticationService authenticationService, UserSession userSession) {
        this.authenticationService = authenticationService;
        this.userSession = userSession;
    }

    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        //uwierzetylnianie
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
        userSession.setSessionID(loginRequest.getUsername()+loginRequest.getPassword());
    }

}
