package pl.edu.pjwstk.jaz.database.repositories;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.entities.AuctionParameter;
import pl.edu.pjwstk.jaz.database.views.AuctionParameterView;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AuctionParameterRepository {

    private final EntityManager entityManager;

    public AuctionParameterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AuctionParameterView> getAuctionParameters(){
        return entityManager
                .createQuery("SELECT v FROM AuctionParameterView v", AuctionParameterView.class)
                .getResultList();
    }

    public List<AuctionParameterView> getAuctionParametersByAuctionId(Long auction_id){
        return entityManager
                .createQuery("SELECT v FROM AuctionParameterView v" +
                        " WHERE v.auctionParameterPK.auction_id =: auction_id", AuctionParameterView.class)
                .setParameter("auction_id", auction_id)
                .getResultList();
    }

}
