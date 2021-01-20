package pl.edu.pjwstk.jaz.controllers.auth0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadinessController {

    @GetMapping("/auth0/is-ready")
    public void readinessTest() {
    }
}
