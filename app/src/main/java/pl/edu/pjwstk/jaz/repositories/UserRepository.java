package pl.edu.pjwstk.jaz.repositories;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;
import pl.edu.pjwstk.jaz.controllers.requests.AuthorityRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
public class UserRepository {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(UserEntity userEntity) {
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
        entityManager.createQuery("SELECT authority FROM AppAuthorityEntity authority WHERE authority.authority = :authority")
                .setParameter("authority", authorityRequest.getAuthority())
                .getSingleResult();
        Set<String> authorities = userEntity.getAuthority();
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

    @Transactional
    public void revokeAuthority(AuthorityRequest authorityRequest) {
        UserEntity userEntity = findUserByUsername(authorityRequest.getUsername());
        Set<String> authorities = userEntity.getAuthority();
        authorities.remove(authorityRequest.getAuthority());
        entityManager.persist(userEntity);
    }
}
