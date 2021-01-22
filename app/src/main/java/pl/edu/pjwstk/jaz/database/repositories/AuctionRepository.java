package pl.edu.pjwstk.jaz.database.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionRepository {

    private final EntityManager entityManager;

    public AuctionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AuctionView> getAuction(){
        List<AuctionView> auctionView = new ArrayList<>();
        return auctionView = entityManager
                .createQuery("SELECT v FROM AuctionView v", AuctionView.class)
                .getResultList();
    }

    public AuctionView getAuctionById(Long id){
        AuctionView auctionView = new AuctionView();
        return auctionView = (AuctionView) entityManager
                .createQuery("SELECT v FROM AuctionView v WHERE v.id =:id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
