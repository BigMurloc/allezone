package pl.edu.pjwstk.jaz.database.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.requests.CategoryRequest;
import pl.edu.pjwstk.jaz.database.entities.Category;
import pl.edu.pjwstk.jaz.database.entities.Section;

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
    public void addCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        Section section = sectionService.findSectionByName(categoryRequest.getSection());
        category.setSection(section);
        category.setName(categoryRequest.getName());
        entityManager.persist(category);
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
}
