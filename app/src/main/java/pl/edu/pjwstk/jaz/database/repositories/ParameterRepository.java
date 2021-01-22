package pl.edu.pjwstk.jaz.database.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.entities.Parameter;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class ParameterRepository {

    private final EntityManager entityManager;

    public ParameterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addParameter(String key){
        if(!doesExistParameterKey(key)){
            Parameter parameter = new Parameter();
            parameter.setKey(key);
            System.out.println("XD");
            entityManager.persist(parameter);
        }
    }

    public Parameter findParameterByKey(String key){
        String query = "SELECT p FROM Parameter p WHERE p.key =: key";
        return entityManager
                .createQuery(query, Parameter.class)
                .setParameter("key", key)
                .getSingleResult();
    }

    public boolean doesExistParameterKey(String key){
        String query = "SELECT count(p) from Parameter p WHERE p.key =: key";
        Long count = (Long) entityManager.createQuery(query)
                .setParameter("key", key)
                .getSingleResult();

        return count != 0;

    }
}
