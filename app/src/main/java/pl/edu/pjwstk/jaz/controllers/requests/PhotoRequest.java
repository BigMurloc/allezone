package pl.edu.pjwstk.jaz.controllers.requests;

public class PhotoRequest {

    private String link;
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
