package pl.edu.pjwstk.jaz.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.SectionRequest;
import pl.edu.pjwstk.jaz.database.entities.Section;
import pl.edu.pjwstk.jaz.database.services.SectionService;
import pl.edu.pjwstk.jaz.exceptions.SectionAlreadyExists;

@RestController
@PreAuthorize("hasAnyAuthority('admin')")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/section")
    public void addSection(@RequestBody SectionRequest sectionRequest) throws SectionAlreadyExists {
        sectionService.addSection(sectionRequest);
    }

    @PutMapping("/section/{id}")
    public void updateSection(@PathVariable Long id, @RequestBody SectionRequest sectionRequest){
        Section section = sectionService.findSectionById(id);
        section.setName(sectionRequest.getName());
        sectionService.updateSection(section);
    }

    @DeleteMapping("/section/{name}")
    public void deleteSection(@PathVariable String name){
        sectionService.deleteSection(name);
    }

}
