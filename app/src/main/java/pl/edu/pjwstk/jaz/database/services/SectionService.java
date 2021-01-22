package pl.edu.pjwstk.jaz.database.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.SectionRequest;
import pl.edu.pjwstk.jaz.database.entities.Section;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class SectionService {

    private final EntityManager entityManager;

    public SectionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addSection(SectionRequest sectionRequest){
        Section section = new Section();
        section.setName(sectionRequest.getName());
        entityManager.persist(section);
    }

    public Section findSectionByName(String section){
        String query = "SELECT s FROM Section s WHERE s.name =: name";
        return (Section) entityManager
                .createQuery(query)
                .setParameter("name", section)
                .getSingleResult();
    }
}
