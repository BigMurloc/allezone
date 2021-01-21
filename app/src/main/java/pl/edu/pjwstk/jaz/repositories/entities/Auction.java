package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private List<Photo> photo;

    private String title;
    private String description;
    private Long price;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserEntity(User user) {
        this.user = user;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setPhotoEntity(List<Photo> photo) {
        this.photo = photo;
    }
}
