package pl.edu.pjwstk.jaz.database.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "auction_view")
@Immutable
public class AuctionView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String price;

    @Column
    private String username;

    @Column
    private String category;

    @Column
    private String section;

    @Column
    private String thumbnail;

//    @OneToMany
//    private List<ParameterView> parameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

//    public List<ParameterView> getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(List<ParameterView> parameters) {
//        this.parameters = parameters;
//    }
}
