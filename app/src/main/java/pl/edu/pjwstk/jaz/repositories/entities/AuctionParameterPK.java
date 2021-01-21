package pl.edu.pjwstk.jaz.repositories.entities;
//https://stackoverflow.com/questions/3585034/how-to-map-a-composite-key-with-jpa-and-hibernate

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuctionParameterPK implements Serializable {

    protected Long auction_id;
    protected Long parameter_id;

    public AuctionParameterPK() {
    }

    public AuctionParameterPK(Long auction_id, Long parameter_id) {
        this.auction_id = auction_id;
        this.parameter_id = parameter_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionParameterPK that = (AuctionParameterPK) o;
        return auction_id.equals(that.auction_id) &&
                parameter_id.equals(that.parameter_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auction_id, parameter_id);
    }
}
