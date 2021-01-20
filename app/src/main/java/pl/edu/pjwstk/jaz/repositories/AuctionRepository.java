package pl.edu.pjwstk.jaz.repositories;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.controllers.requests.AuctionRequest;
import pl.edu.pjwstk.jaz.repositories.entities.AuctionEntity;
import pl.edu.pjwstk.jaz.repositories.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class AuctionRepository {

    private final EntityManager entityManager;

    public AuctionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addAuction(AuctionRequest auctionRequest){
        AuctionEntity auctionEntity = new AuctionEntity();
        UserEntity currentUser =
                (UserEntity) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        auctionEntity.setUserEntity(currentUser);
        auctionEntity.setTitle(auctionRequest.getTitle());
        auctionEntity.setDescription(auctionRequest.getDescription());
        auctionEntity.setPrice(auctionRequest.getPrice());

        entityManager.persist(auctionEntity);
    }

}
