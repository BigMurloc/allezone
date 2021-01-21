package pl.edu.pjwstk.jaz.repositories;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.controllers.requests.PhotoRequest;
import pl.edu.pjwstk.jaz.repositories.entities.Auction;
import pl.edu.pjwstk.jaz.repositories.entities.Photo;
import pl.edu.pjwstk.jaz.repositories.entities.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionRepository {

    private final EntityManager entityManager;

    public AuctionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addAuction(AuctionRequest auctionRequest){
        Auction auction = new Auction();
        User currentUser =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        auction.setUserEntity(currentUser);
        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());
        auction.setPhotoEntity(addPhoto(auctionRequest.getPhotos()));

        entityManager.persist(auction);
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

}
