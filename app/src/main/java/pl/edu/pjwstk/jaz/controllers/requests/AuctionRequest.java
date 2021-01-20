package pl.edu.pjwstk.jaz.controllers.requests;

import java.util.List;

public class AuctionRequest {

    private String title;
    private String description;
    private Long price;
    private List<PhotoRequest> photo;

    public List<PhotoRequest> getPhotos() {
        return photo;
    }

    public void setPhoto(List<PhotoRequest> photo) {
        this.photo = photo;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
