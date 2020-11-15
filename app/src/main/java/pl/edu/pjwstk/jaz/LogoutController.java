package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    private final UserSession userSession;

    public LogoutController(UserSession userSession) {
        this.userSession = userSession;
    }

    @PostMapping("auth0/logout")
    public void logout(){
        userSession.setSessionID(null);
    }

}
