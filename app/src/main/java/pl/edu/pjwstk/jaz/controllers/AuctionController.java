package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.database.repositories.AuctionRepository;
import pl.edu.pjwstk.jaz.database.services.AuctionService;
import pl.edu.pjwstk.jaz.database.services.CategoryService;
import pl.edu.pjwstk.jaz.database.services.SectionService;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import java.util.List;

@RestController
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final AuctionService auctionService;
    private final CategoryService categoryService;
    private final SectionService sectionService;

    public AuctionController(AuctionRepository auctionRepository,
                             AuctionService auctionService,
                             CategoryService categoryService,
                             SectionService sectionService) {
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
        this.categoryService = categoryService;
        this.sectionService = sectionService;
    }

    @PostMapping("/auction")
    public void addAuction(@RequestBody AuctionRequest auctionRequest){
        auctionService.addAuction(auctionRequest);
    }

    @PostMapping("/section")
    public void addSection(@RequestBody SectionRequest sectionRequest){
        sectionService.addSection(sectionRequest);
    }

    @PostMapping("/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.addCategory(categoryRequest);
    }

    @GetMapping("/auction")
    public List<AuctionView> getAuction(){
        return auctionRepository.getAuction();
    }

    @GetMapping("/auction/{id}")
    public AuctionView getAuctionById(@PathVariable Long id){
        return auctionRepository.getAuctionById(id);
    }

}
