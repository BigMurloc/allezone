package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.database.services.AuthenticationService;
import pl.edu.pjwstk.jaz.controllers.requests.LoginRequest;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;
import pl.edu.pjwstk.jaz.database.services.FilterAuthenticationService;
import pl.edu.pjwstk.jaz.exceptions.UserDoesNotExistException;

@RestController
public class LoginController {


    private final AuthenticationService authenticationService;
    private final FilterAuthenticationService filterAuthenticationService;

    public LoginController(AuthenticationService authenticationService, FilterAuthenticationService filterAuthenticationService) {
        this.authenticationService = authenticationService;
        this.filterAuthenticationService = filterAuthenticationService;
    }

    //filter
    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException, UserDoesNotExistException {
        var isLogged = filterAuthenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

    //spring
    @PostMapping("/login")
    public void loginSpring(@RequestBody LoginRequest loginRequest) throws UnauthorizedException, UserDoesNotExistException {
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

}
