package pl.edu.pjwstk.jaz.repositories.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parameter")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String key;

    @OneToMany(mappedBy = "parameter", cascade = CascadeType.MERGE)
    private List<AuctionParameter> auctionParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
