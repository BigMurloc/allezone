package pl.edu.pjwstk.jaz.database.services;


import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.database.entities.Auction;
import pl.edu.pjwstk.jaz.database.entities.AuctionParameter;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class AuctionParameterService {

    private final EntityManager entityManager;

    public AuctionParameterService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void deleteOldParameters(Auction auction){
        String query = "DELETE FROM AuctionParameter WHERE auctionParameterPK.auction_id=:id";
        entityManager
                .createQuery(query)
                .setParameter("id", auction.getId())
                .executeUpdate();
    }

}
