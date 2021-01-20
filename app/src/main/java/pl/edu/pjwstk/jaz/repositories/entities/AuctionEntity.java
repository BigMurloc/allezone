package pl.edu.pjwstk.jaz.repositories.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity userEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private List<PhotoEntity> photoEntity;

    private String title;
    private String description;
    private Long price;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setPhotoEntity(List<PhotoEntity> photoEntity) {
        this.photoEntity = photoEntity;
    }
}
