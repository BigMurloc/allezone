package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.*;

@Entity
@Table(name = "auction_parameter")
public class AuctionParameter {

    @EmbeddedId
    private AuctionParameterPK auctionParameterPK = new AuctionParameterPK();

    private String value;


    @ManyToOne
    @MapsId("auction_id")
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @MapsId("parameter_id")
    @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    private Parameter parameter;


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

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
