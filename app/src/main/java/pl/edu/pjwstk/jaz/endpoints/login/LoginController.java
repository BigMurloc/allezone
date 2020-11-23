package pl.edu.pjwstk.jaz.endpoints.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.authentication.AuthenticationService;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;

@RestController
public class LoginController {


    private final AuthenticationService authenticationService;
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

}
