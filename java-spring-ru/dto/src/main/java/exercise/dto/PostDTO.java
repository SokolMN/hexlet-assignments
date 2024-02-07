package exercise.dto;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Comment;
import lombok.Getter;
import lombok.Setter;

// BEGIN
public class PostDTO {
    private long id;
    private String title;
    private String body;
    private ArrayList<CommentDTO> comments;

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setComments(ArrayList<CommentDTO> comments){
        this.comments = comments;
    }


}
// END
