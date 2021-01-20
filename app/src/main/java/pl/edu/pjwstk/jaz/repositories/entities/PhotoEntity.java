package pl.edu.pjwstk.jaz.repositories.entities;


import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionEntity auctionEntity;


    private String link;
    @Column(name = "\"order\"")
    private int order;

    public void setOrder(int order) {
        this.order = order;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
