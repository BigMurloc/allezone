package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.CategoryRequest;
import pl.edu.pjwstk.jaz.database.entities.Category;
import pl.edu.pjwstk.jaz.database.entities.Section;
import pl.edu.pjwstk.jaz.database.services.CategoryService;
import pl.edu.pjwstk.jaz.database.services.SectionService;
import pl.edu.pjwstk.jaz.exceptions.CategoryAlreadyExistsException;

@RestController
@PreAuthorize("hasAnyAuthority('admin')")
public class CategoryController {

    private final CategoryService categoryService;
    private final SectionService sectionService;

    public CategoryController(CategoryService categoryService,
                              SectionService sectionService) {
        this.categoryService = categoryService;
        this.sectionService = sectionService;
    }

    @PostMapping("/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryAlreadyExistsException {
        categoryService.addCategory(categoryRequest);
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.findCategoryById(id);
        Section updatedSection = sectionService.findSectionByName(categoryRequest.getSection());
        category.setName(categoryRequest.getName());
        category.setSection(updatedSection);
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/category/{name}")
    public void deleteCategory(@PathVariable String name){
        categoryService.deleteCategory(name);
    }

}
