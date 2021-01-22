package pl.edu.pjwstk.jaz.database.repositories;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.database.entities.User;
import pl.edu.pjwstk.jaz.controllers.requests.AuthorityRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findUserByUsername(String username){
        return entityManager.createQuery("select ue from User ue where ue.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Transactional
    public void saveUser(RegisterRequest registerRequest) {
        User user = new User();
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPesel(registerRequest.getPesel());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAuthority(new HashSet<>());
        entityManager.persist(user);
    }

    @Transactional
    public void deleteUser(String username) {
        User user = findUserByUsername(username);
        entityManager.remove(user);
    }

    @Transactional
    public void grantAuthority(AuthorityRequest authorityRequest){
        User user = findUserByUsername(authorityRequest.getUsername());
        entityManager.createQuery("SELECT authority FROM Authority authority WHERE authority.authority = :authority")
                .setParameter("authority", authorityRequest.getAuthority())
                .getSingleResult();
        Set<String> authorities = user.getAuthority();
        authorities.add(authorityRequest.getAuthority());
        entityManager.persist(user);
    }

    @Transactional
    public void revokeAuthority(AuthorityRequest authorityRequest) {
        User user = findUserByUsername(authorityRequest.getUsername());
        Set<String> authorities = user.getAuthority();
        authorities.remove(authorityRequest.getAuthority());
        entityManager.persist(user);
    }

    public boolean matches(String rawPassword, String hashedPassword){
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
