package pl.edu.pjwstk.jaz.database.views;

import pl.edu.pjwstk.jaz.database.entities.AuctionParameterPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auction_parameter_view" )
public class AuctionParameterView {

    @EmbeddedId
    private AuctionParameterPK auctionParameterPK;

    private String key;
    private String value;

    public AuctionParameterPK getAuctionParameterPK() {
        return auctionParameterPK;
    }

    public void setAuctionParameterPK(AuctionParameterPK auctionParameterPK) {
        this.auctionParameterPK = auctionParameterPK;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
