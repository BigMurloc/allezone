package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AuctionParameterEntity {

    @EmbeddedId
    private AuctionParameterPK auctionParameterPK;

    private String value;

    public void setValue(String value) {
        this.value = value;
    }
}
