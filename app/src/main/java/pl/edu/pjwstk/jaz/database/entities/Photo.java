package pl.edu.pjwstk.jaz.database.entities;


import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

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
