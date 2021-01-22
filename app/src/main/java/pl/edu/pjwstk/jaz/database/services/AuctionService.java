package pl.edu.pjwstk.jaz.database.services;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.controllers.requests.ParameterRequest;
import pl.edu.pjwstk.jaz.controllers.requests.PhotoRequest;
import pl.edu.pjwstk.jaz.database.entities.*;
import pl.edu.pjwstk.jaz.database.repositories.ParameterRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    private final EntityManager entityManager;
    private final ParameterRepository parameterRepository;
    private final CategoryService categoryService;

    public AuctionService(EntityManager entityManager, ParameterRepository parameterRepository, CategoryService categoryService) {
        this.entityManager = entityManager;
        this.parameterRepository = parameterRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public void addAuction(AuctionRequest auctionRequest){
        entityManager.persist(setUpAuction(new Auction(), auctionRequest));
    }

    public Auction setUpAuction(Auction auction, AuctionRequest auctionRequest) {
        Category category = categoryService.findCategoryByName(auctionRequest.getCategory());
        User currentUser =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        auction.setUser(currentUser);
        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());
        auction.setPhoto(addPhoto(auctionRequest.getPhotos()));
        auction.setAuctionParameters(addAuctionParameter(auctionRequest.getParameters(), auction));
        auction.setCategory(category);
        return auction;
    }


    //private------
    private List<Photo> addPhoto(List<PhotoRequest> photos){
        List<Photo> photoList = new ArrayList<>();
        for(PhotoRequest photo : photos){
            Photo photoEntity = new Photo();
            photoEntity.setLink(photo.getLink());
            photoEntity.setOrder(photo.getOrder());
            photoList.add(photoEntity);
        }
        return photoList;
    }

    private List<AuctionParameter> addAuctionParameter(List<ParameterRequest> parameters, Auction auction){
        List<AuctionParameter> auctionParameters = new ArrayList<>();

        for(ParameterRequest parameterRequest : parameters){
            AuctionParameter auctionParameter = new AuctionParameter();


            parameterRepository.addParameter(parameterRequest.getKey());
            Parameter parameter = parameterRepository.findParameterByKey(parameterRequest.getKey());

            auctionParameter.setAuction(auction);
            auctionParameter.setParameter(parameter);
            auctionParameter.setValue(parameterRequest.getValue());

            auctionParameters.add(auctionParameter);
        }

        return auctionParameters;
    }

}
