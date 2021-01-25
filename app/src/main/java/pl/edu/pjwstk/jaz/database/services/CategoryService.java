package pl.edu.pjwstk.jaz.database.services;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.requests.CategoryRequest;
import pl.edu.pjwstk.jaz.database.entities.Category;
import pl.edu.pjwstk.jaz.database.entities.Section;
import pl.edu.pjwstk.jaz.exceptions.CategoryAlreadyExists;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class CategoryService {

    private final EntityManager entityManager;
    private final SectionService sectionService;

    public CategoryService(EntityManager entityManager, SectionService sectionService) {
        this.entityManager = entityManager;
        this.sectionService = sectionService;
    }

    @Transactional
    public void addCategory(CategoryRequest categoryRequest) throws CategoryAlreadyExists {

        if(findCategoryByName(categoryRequest.getName()) != null){
            throw new CategoryAlreadyExists();
        }
        Category category = new Category();
        category.setName(categoryRequest.getName());

        Section section = sectionService.findSectionByName(categoryRequest.getSection());
        category.setSection(section);
        try {
            entityManager.persist(category);
        } catch (ConstraintViolationException e){
            System.out.println("oLALALALA");
        }
    }

    @Transactional
    public void updateCategory(Category category){
        entityManager.merge(category);
    }

    public Category findCategoryByName(String category){
        String query = "SELECT c FROM Category c WHERE c.name =: name";
        return (Category) entityManager
                .createQuery(query)
                .setParameter("name", category)
                .getSingleResult();
    }

    public Category findCategoryById(Long id) {
        String query = "SELECT c FROM Category c WHERE c.id =: id";
        return (Category) entityManager
                .createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void deleteCategory(String name) {
        Category category = findCategoryByName(name);
        entityManager.remove(category);
    }
}
