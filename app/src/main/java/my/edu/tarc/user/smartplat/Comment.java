package my.edu.tarc.user.smartplat;

public class Comment {
    private String CommentUser;
    private int CommentTime;
    private String CommentDescription;
    private int CommentLike;
    private String EventTitle;

    public Comment(String commentUser, int commentTime, String commentDescription, int commentLike, String eventTitle) {

        CommentUser = commentUser;
        CommentTime = commentTime;
        CommentDescription = commentDescription;
        CommentLike = commentLike;
        EventTitle = eventTitle;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String eventTitle) {
        EventTitle = eventTitle;
    }

    public String getCommentUser() {
        return CommentUser;
    }

    public void setCommentUser(String commentUser) {
        CommentUser = commentUser;
    }

    public int getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(int commentTime) {
        CommentTime = commentTime;
    }

    public String getCommentDescription() {
        return CommentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        CommentDescription = commentDescription;
    }

    public int getCommentLike() {
        return CommentLike;
    }

    public void setCommentLike(int commentLike) {
        CommentLike = commentLike;
    }
}
