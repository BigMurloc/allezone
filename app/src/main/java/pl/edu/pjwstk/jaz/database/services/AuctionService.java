package pl.edu.pjwstk.jaz.database.services;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.controllers.CategoryRequest;
import pl.edu.pjwstk.jaz.controllers.SectionRequest;
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

    public AuctionService(EntityManager entityManager, ParameterRepository parameterRepository) {
        this.entityManager = entityManager;
        this.parameterRepository = parameterRepository;
    }

    @Transactional
    public void addAuction(AuctionRequest auctionRequest){
        Auction auction = new Auction();
        Category category = findCategoryByName(auctionRequest.getCategory());
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

        entityManager.persist(auction);
    }

    @Transactional
    public void addSection(SectionRequest sectionRequest){
        Section section = new Section();
        section.setName(sectionRequest.getName());
        entityManager.persist(section);
    }

    @Transactional
    public void addCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        Section section = findSectionByName(categoryRequest.getSection());
        category.setSection(section);
        category.setName(categoryRequest.getName());
        entityManager.persist(category);
    }

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

    private Section findSectionByName(String section){
        String query = "SELECT s FROM Section s WHERE s.name =: name";
        return (Section) entityManager
                .createQuery(query)
                .setParameter("name", section)
                .getSingleResult();
    }

    private Category findCategoryByName(String category){
        String query = "SELECT c FROM Category c WHERE c.name =: name";
        return (Category) entityManager
                .createQuery(query)
                .setParameter("name", category)
                .getSingleResult();
    }

}
