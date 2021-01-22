package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.repositories.AuctionRepository;

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

}
