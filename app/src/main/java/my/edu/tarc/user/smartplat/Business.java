package my.edu.tarc.user.smartplat;

public class Business {
    private String title;
    private String description;
    private String operationTime;
    private String venue;
    private int image;
    private String url;

    public Business(String title, String description, String operationTime, String venue, int image, String url) {
        this.title = title;
        this.description = description;
        this.operationTime = operationTime;
        this.venue = venue;
        this.image = image;
        this.url = url;
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

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", opreationTime='" + operationTime + '\'' +
                ", venue='" + venue + '\'' +
                ", image=" + image +
                '}';
    }
}
