package pl.edu.pjwstk.jaz.controllers;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.database.entities.Auction;
import pl.edu.pjwstk.jaz.database.repositories.AuctionParameterRepository;
import pl.edu.pjwstk.jaz.database.repositories.AuctionRepository;
import pl.edu.pjwstk.jaz.database.services.AuctionParameterService;
import pl.edu.pjwstk.jaz.database.services.AuctionService;
import pl.edu.pjwstk.jaz.database.views.AuctionParameterView;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import java.util.List;

@RestController
public class AuctionController {

    private final AuctionRepository auctionRepository;
    private final AuctionService auctionService;
    private final AuctionParameterService auctionParameterService;
    private final AuctionParameterRepository auctionParameterRepository;

    public AuctionController(AuctionRepository auctionRepository,
                             AuctionService auctionService, AuctionParameterService auctionParameterService, AuctionParameterRepository auctionParameterRepository) {
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
        this.auctionParameterService = auctionParameterService;
        this.auctionParameterRepository = auctionParameterRepository;
    }

    @GetMapping("/auction")
    public List<AuctionView> getAuction(){
        return auctionRepository.getAuction();
    }

    @GetMapping("/auction/{id}")
    public AuctionView getAuctionById(@PathVariable Long id){
        return auctionRepository.getAuctionById(id);
    }

    @PostMapping("/auction")
    public void addAuction(@RequestBody AuctionRequest auctionRequest){
        auctionService.addAuction(auctionRequest);
    }

    @PutMapping("/auction/{id}")
    public void updateAuction(@PathVariable Long id, @RequestBody AuctionRequest auctionRequest){
        Auction auction = auctionService.findAuctionById(id);
        if(auction.getVersion().equals(auctionRequest.getVersion())) {
            auctionParameterService.deleteOldParameters(auction);
            auctionService.setUpAuction(auction, auctionRequest);
            auctionService.updateAuction(auction);
        }
    }

    @GetMapping("/parameter")
    public  List<AuctionParameterView> getParameter(){
        return auctionParameterRepository.getAuctionParameters();
    }

}
