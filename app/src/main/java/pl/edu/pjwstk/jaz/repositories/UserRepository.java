package pl.edu.pjwstk.jaz.repositories;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.controllers.requests.RegisterRequest;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;
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

    public UserEntity findUserByUsername(String username){
        return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Transactional
    public void saveUser(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
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
        entityManager
                .createQuery("DELETE FROM UserEntity ue WHERE ue.username = :username")
                .setParameter("username", username)
                .executeUpdate();
    }

    @Transactional
    public void grantAuthority(AuthorityRequest authorityRequest){
        UserEntity userEntity = findUserByUsername(authorityRequest.getUsername());
        entityManager.createQuery("SELECT authority FROM AppAuthorityEntity authority WHERE authority.authority = :authority")
                .setParameter("authority", authorityRequest.getAuthority())
                .getSingleResult();
        Set<String> authorities = userEntity.getAuthority();
        authorities.add(authorityRequest.getAuthority());
        entityManager.persist(userEntity);
    }

    @Transactional
    public void revokeAuthority(AuthorityRequest authorityRequest) {
        UserEntity userEntity = findUserByUsername(authorityRequest.getUsername());
        Set<String> authorities = userEntity.getAuthority();
        authorities.remove(authorityRequest.getAuthority());
        entityManager.persist(userEntity);
    }

    public boolean matches(String rawPassword, String hashedPassword){
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
