package pl.edu.pjwstk.jaz;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    private final AuthenticationService authenticationService;
    private final FilterAuthenthicationService filterAuthenthicationService;
    public LoginController(AuthenticationService authenticationService, FilterAuthenthicationService filterAuthenthicationService) {
        this.authenticationService = authenticationService;
        this.filterAuthenthicationService = filterAuthenthicationService;
    }

    //filter
    @PostMapping(value = "/auth0/login")
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
