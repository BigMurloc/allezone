package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.database.repositories.AuctionRepository;
import pl.edu.pjwstk.jaz.database.services.AuctionService;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import java.util.List;

@RestController
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final AuctionService auctionService;

    public AuctionController(AuctionRepository auctionRepository, AuctionService auctionService) {
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
    }

    @PostMapping("/auction")
    public void addAuction(@RequestBody AuctionRequest auctionRequest){
        auctionService.addAuction(auctionRequest);
    }

    @PostMapping("/section")
    public void addSection(@RequestBody SectionRequest sectionRequest){
        auctionService.addSection(sectionRequest);
    }

    @PostMapping("/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        auctionService.addCategory(categoryRequest);
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
