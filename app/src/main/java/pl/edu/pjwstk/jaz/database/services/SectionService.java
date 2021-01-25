package pl.edu.pjwstk.jaz.database.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.requests.SectionRequest;
import pl.edu.pjwstk.jaz.database.entities.Section;
import pl.edu.pjwstk.jaz.exceptions.SectionAlreadyExistException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class SectionService {

    private final EntityManager entityManager;

    public SectionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addSection(SectionRequest sectionRequest) throws SectionAlreadyExistException {
        Section section = new Section();
        section.setName(sectionRequest.getName());
        if(doesExistByName(sectionRequest.getName())){
            throw new SectionAlreadyExistException();
        }
        entityManager.persist(section);
    }

    private boolean doesExistByName(String name) {
        String query = "SELECT count(s) FROM Section s WHERE s.name =: name";
        Long count = (Long) entityManager
                .createQuery(query)
                .setParameter("name", name)
                .getSingleResult();

        return count != 0;
    }

    public Section findSectionById(Long id){
        String query = "SELECT s FROM Section s WHERE s.id =: id";
        return (Section) entityManager
                .createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Section findSectionByName(String name){
        String query = "SELECT s FROM Section s WHERE s.name =: name";
        return (Section) entityManager
                .createQuery(query)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Transactional
    public void updateSection(Section section) {
        entityManager.merge(section);
    }

    @Transactional
    public void deleteSection(String name){
        Section section = findSectionByName(name);
        entityManager.remove(section);
    }
}
