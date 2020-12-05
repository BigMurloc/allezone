package pl.edu.pjwstk.jaz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.entities.UserEntity;
import pl.edu.pjwstk.jaz.requests.RegisterRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        entityManager.persist(userEntity);
    }

    public UserEntity findUserByUsername(String username){
        return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
