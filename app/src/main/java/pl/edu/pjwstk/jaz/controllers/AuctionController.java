package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.database.repositories.AuctionRepository;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import java.util.List;

@RestController
public class AuctionController {

    private final AuctionRepository auctionRepository;

    public AuctionController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @PostMapping("/auction")
    public void addAuction(@RequestBody AuctionRequest auctionRequest){
        auctionRepository.addAuction(auctionRequest);
    }

    @PostMapping("/section")
    public void addSection(@RequestBody SectionRequest sectionRequest){
        auctionRepository.addSection(sectionRequest);
    }

    @PostMapping("/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        auctionRepository.addCategory(categoryRequest);
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
