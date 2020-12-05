package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.services.AuthenticationService;
import pl.edu.pjwstk.jaz.services.FilterAuthenthicationService;
import pl.edu.pjwstk.jaz.requests.LoginRequest;
import pl.edu.pjwstk.jaz.exceptions.UnauthorizedException;

@RestController
public class LoginController {


    private final AuthenticationService authenticationService;
    private final FilterAuthenthicationService filterAuthenthicationService;
    public LoginController(AuthenticationService authenticationService, FilterAuthenthicationService filterAuthenthicationService) {
        this.authenticationService = authenticationService;
        this.filterAuthenthicationService = filterAuthenthicationService;
    }

    //filter
    @PostMapping("/auth0/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        var isLogged = filterAuthenthicationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

    //spring
    @PostMapping("/login")
    public void loginSpring(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(!isLogged){
            throw new UnauthorizedException();
        }
    }

}