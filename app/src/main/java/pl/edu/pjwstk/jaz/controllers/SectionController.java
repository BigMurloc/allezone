package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.SectionRequest;
import pl.edu.pjwstk.jaz.database.entities.Section;
import pl.edu.pjwstk.jaz.database.services.SectionService;

@RestController
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/section")
    public void addSection(@RequestBody SectionRequest sectionRequest){
        sectionService.addSection(sectionRequest);
    }

    @PutMapping("/section/{id}")
    public void updateSection(@PathVariable Long id, @RequestBody SectionRequest sectionRequest){
        Section section = sectionService.findSectionById(id);
        section.setName(sectionRequest.getName());
        sectionService.updateSection(section);
    }

}
