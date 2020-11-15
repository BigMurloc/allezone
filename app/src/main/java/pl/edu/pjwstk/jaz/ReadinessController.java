package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinessController {
    @PersistenceContext
    private EntityManager entityManager;

    private final UserSession userSession;

    public ReadinessController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("auth0/is-ready")
    @Transactional
    public String readinessTest() {
        var entity = new Test1Entity();
        entity.setName("sdavsda");
        entityManager.persist(entity);
        return userSession.getSessionID();
    }
}
