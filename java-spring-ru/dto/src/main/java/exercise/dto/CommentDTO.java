package exercise.dto;

import lombok.Getter;
import lombok.Setter;

// BEGIN
public class CommentDTO {

    private long id;
    private String body;

    public void setId(long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }


}
// END
