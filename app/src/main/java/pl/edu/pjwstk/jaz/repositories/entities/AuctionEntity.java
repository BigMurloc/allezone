package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.*;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity userEntity;

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
}
