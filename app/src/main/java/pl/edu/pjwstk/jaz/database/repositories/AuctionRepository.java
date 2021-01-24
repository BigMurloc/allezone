package pl.edu.pjwstk.jaz.database.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.views.AuctionView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionRepository {

    private final EntityManager entityManager;
    private final AuctionParameterRepository auctionParameterRepository;

    public AuctionRepository(EntityManager entityManager,
                             AuctionParameterRepository auctionParameterRepository) {
        this.entityManager = entityManager;
        this.auctionParameterRepository = auctionParameterRepository;
    }

    public List<AuctionView> getAuction(){
        List<AuctionView> auctionViews;
        auctionViews = entityManager
                .createQuery("SELECT v FROM AuctionView v", AuctionView.class)
                .getResultList();
        for (AuctionView auctionView : auctionViews) {
            auctionView.setParameters(auctionParameterRepository.getAuctionParametersByAuctionId(auctionView.getId()));
        }
        return auctionViews;
    }

    public AuctionView getAuctionById(Long id){
        AuctionView auctionView;
        auctionView = entityManager
                .createQuery("SELECT v FROM AuctionView v WHERE v.id =:id", AuctionView.class)
                .setParameter("id", id)
                .getSingleResult();
        auctionView.setParameters(auctionParameterRepository.getAuctionParametersByAuctionId(auctionView.getId()));
        return auctionView;
    }

}
