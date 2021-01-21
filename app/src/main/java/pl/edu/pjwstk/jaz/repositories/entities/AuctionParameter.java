package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AuctionParameter {

    @EmbeddedId
    private AuctionParameterPK auctionParameterPK;

    private String value;

    public AuctionParameterPK getAuctionParameterPK() {
        return auctionParameterPK;
    }

    public void setAuctionParameterPK(AuctionParameterPK auctionParameterPK) {
        this.auctionParameterPK = auctionParameterPK;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
