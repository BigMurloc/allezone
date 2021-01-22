package pl.edu.pjwstk.jaz.repositories.views;

import org.hibernate.annotations.Immutable;
import pl.edu.pjwstk.jaz.repositories.entities.AuctionParameterPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auction_parameter_view")
@Immutable
public class ParameterView {

    @EmbeddedId
    AuctionParameterPK auctionParameterPK;

    @Column(insertable = false, updatable = false)
    private Long auction_id = auctionParameterPK.getAuction_id();

    @Column(insertable = false, updatable = false)
    private Long parameter_id = auctionParameterPK.getParameter_id();

    @Column
    private String key;

    @Column
    private String value;


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

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }
}
