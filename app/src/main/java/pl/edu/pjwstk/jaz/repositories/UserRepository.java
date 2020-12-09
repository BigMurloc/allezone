package pl.edu.pjwstk.jaz.repositories;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.entities.UserEntity;
import pl.edu.pjwstk.jaz.requests.AuthorityRequest;
import pl.edu.pjwstk.jaz.requests.RegisterRequest;

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

    @Transactional
    public void saveUser(RegisterRequest registerRequest){
        var userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        var encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        userEntity.setPassword(encodedPassword);
        userEntity.setAuthorities(new HashSet<>());
        entityManager.persist(userEntity);
    }

    public UserEntity findUserByUsername(String username){
        return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Transactional
    public void grantAuthority(AuthorityRequest authorityRequest){
        UserEntity userEntity = findUserByUsername(authorityRequest.getUsername());
        Set<String> authorities = userEntity.getAuthorities();
        authorities.add(authorityRequest.getAuthority());
        entityManager.persist(userEntity);
    }


    @Transactional
    public void deleteUser(String username) {
        entityManager
                .createQuery("DELETE FROM UserEntity ue WHERE ue.username = :username")
                .setParameter("username", username)
                .executeUpdate();
    }
}
